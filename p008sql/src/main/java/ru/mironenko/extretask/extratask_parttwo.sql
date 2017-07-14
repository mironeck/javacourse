select company.name, count(*) as cnt
from company, person where person.company_id = company.id
group by company.name
order by cnt desc
limit 1;