
# Task-Management-System

  Task-ების შექმნის და განაწილების ამ სისტემაში შესაძლებელია პირველ რიგში როლების შექმნა, 
რაც გულისხმობს სხვადასხვა უფლებების მქონე სტატუსის დამატებას (მაგალითად, პირობითად, მენეჯერს შეუძლია Task-ის ნახვაც, შექმნაც, შეცვლაც 
და წაშლაც, როცა ტესტერს - მხოლოდ ნახვა და შეცვლა). როცა აპლიკაცია ისტარტება, რამდენიმე ნაგულისხმევი როლი ავტომატურად ემატება,
თუმცა სხვადასხვა როლების მოფიქრება და შენახვაც, რა თქმა უნდა, შესაძლებელია. 
  როლების დამატების შემდეგ მომხმარებლების შექმნაც შეიძლება შესაბამისი როლებით. მომხმარებელს აუცილებლად უნდა გააჩნდეს სახელი 
და როლი(სტატუსი), რათა შემდგომში ამ უკანასკნელის მიხედვით შეძლოს Task-ებზე მანიპულაციები. 
  რაც შეეხება Task-ებს, მათ მომხმარებელი ამატებს (თუ, რა თქმა უნდა, აქვს ამისი უფლება) და შესაბამის მომხმარებელს (საკუთარ თავს ან სხვას)
მიაბამს.

  პროექტს არ აქვს View-ს ნაწილი, შესაბამისად სხვადასხვა Request-ების გაგზავნა შესაძლებელია generated-requests.http ფაილიდან, რომელიც 
HttpRequest Package-ში არსებობს. ამ ფაილში ყველა შესაძლო Request-ის მაგალითი უკვე მოცემულია და კომენტარებში განმარტებულია 
რა რას აკეთებს. 
