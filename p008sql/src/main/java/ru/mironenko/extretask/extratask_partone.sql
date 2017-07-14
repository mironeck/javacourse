select p.name, c.name, c.id 
from person as p inner join company as c 
on p.company_id = c.id and p.company_id != 5;