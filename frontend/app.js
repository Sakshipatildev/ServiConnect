const API="http://localhost:8080/api";
const list=document.getElementById("list");
async function load(category=""){
 list.textContent="Loading...";
 try{
  const response=await fetch(API+"/services"+(category?"?category="+encodeURIComponent(category):""));
  if(!response.ok)throw new Error("Backend returned "+response.status);
  const data=await response.json();
  if(!data.length){list.innerHTML='<p>No services listed yet. Provider listings will appear here.</p>';return;}
  list.innerHTML=data.map(s=>`<article class="card"><div>🛠️</div><h3>${safe(s.Title)}</h3><p>${safe(s.Description||"Local professional service")}</p><p>Provider: ${safe(s.ProviderName)}</p><div class="price">₹${Number(s.Price).toFixed(2)} / ${s.PricingType==="HOURLY"?"hour":"service"}</div></article>`).join("");
 }catch(e){list.textContent="Could not load services. Start the Spring Boot backend and configure SQL Server. "+e.message;}
}
function safe(v){return String(v).replace(/[&<>"']/g,c=>({"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#39;"}[c]));}
document.querySelectorAll("[data-cat]").forEach(b=>b.addEventListener("click",()=>load(b.dataset.cat)));
load();
