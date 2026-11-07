package com.serviconnect;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.*;
@RestController @RequestMapping("/api")
@CrossOrigin(origins={"http://localhost:5500","http://127.0.0.1:5500","null"})
public class ApiController {
 private final JdbcTemplate db;
 public ApiController(JdbcTemplate db){this.db=db;}
 @GetMapping("/health") public Map<String,String> health(){return Map.of("status","UP","app","ServiConnect");}
 @GetMapping("/services") public List<Map<String,Object>> services(@RequestParam(required=false) String category){
  String sql="SELECT s.Id,s.ProviderId,u.FullName AS ProviderName,s.Category,s.Title,s.Description,s.PricingType,s.Price FROM Services s JOIN Users u ON u.Id=s.ProviderId WHERE s.IsActive=1";
  if(category!=null&&!category.isBlank()) return db.queryForList(sql+" AND s.Category=? ORDER BY s.Id DESC",category);
  return db.queryForList(sql+" ORDER BY s.Id DESC");
 }
 @PostMapping("/services") public Map<String,Object> addService(@RequestHeader("X-User-Id") long providerId,@RequestBody Map<String,Object> b){
  String role=db.queryForObject("SELECT Role FROM Users WHERE Id=?",String.class,providerId);
  if(!"PROVIDER".equals(role)) throw new IllegalArgumentException("Provider account required");
  db.update("INSERT INTO Services(ProviderId,Category,Title,Description,PricingType,Price) VALUES(?,?,?,?,?,?)",
   providerId,b.get("category"),b.get("title"),b.get("description"),b.get("pricingType"),b.get("price"));
  return Map.of("message","Service added");
 }
}
