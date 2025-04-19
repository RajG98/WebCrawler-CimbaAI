from fastapi import FastAPI
from pydantic import BaseModel
import requests
from bs4 import BeautifulSoup
from openai import OpenAI
from dotenv import load_dotenv
import os
load_dotenv()
client = OpenAI(api_key=os.getenv("API_KEY"))
app = FastAPI()

class CrawlRequest(BaseModel):
    url: str
    prompt: str = "Summarize this page:"

def extract_text_from_url(url: str) -> str:
    response = requests.get(url)
    soup = BeautifulSoup(response.content, "html.parser")
    for tag in soup(["script", "style"]):
        tag.decompose()
    return soup.get_text(separator="\n", strip=True)

def ask_openai(prompt: str, context: str) -> str:
    full_prompt = f"{prompt}\n\n{context[:4000]}"
    response = 	client.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role": "user", "content": full_prompt}
        ]
    )
    return response.choices[0].message.content

@app.post("/crawl/")
def crawl_page(request: CrawlRequest):
    try:
        page_text = extract_text_from_url(request.url)
        answer = ask_openai(request.prompt, page_text)
        return {"response": answer}
    except Exception as e:
        return {"error": str(e)}
