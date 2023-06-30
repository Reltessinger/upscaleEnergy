# Upscale Energy
# Introdução

O projeto Upscale Energy foi criado para disponibilizar uma API para gerenciar o consumo de energia de cada eletrodoméstico por imóvel, essa informação depois pode ser consultada por um projeto frontend e exibir a informação em um painel de controle.

Link do projeto no github: [UpscaleEnergy](https://github.com/Reltessinger/upscaleEnergy)

# Primeira Fase

Na primeira fase foi implementado um CRUD com persistência em memória para cada entidade Pessoa, Endereço e Eletrodoméstico, são realizadas validações antes de inserir ou atualizar uma informação para garantir que os dados enviados são válidos.

## Entidades e Validações

1.	Address</br>
O objeto endereço tem os campos id, street, number, district, postalCode, city, state, country, active.</br></br>
•	Validações</br>
street - deve ter entre 5 e 100 caracteres</br>
number - tem de ser um número positivo</br>
district - deve ter entre 3 e 100 caracteres</br>
postalCode - o formato enviado dever ser ddddd-ddd, exemplo 12345-000</br>
city - deve ter entre 5 e 100 caracteres</br>
state - deve ter entre 3 e 100 caracteres</br>
country - deve ter entre 5 e 100 caracteres</br>
active - é obrigatório</br>

2.	Appliance</br>
O objeto Eletrodoméstico tem os campos id, name, model, power, powerUnit, active.</br></br>
•	Validações</br>
name - mínimo 5 caracteres </br>
model – é obrigatório</br>
power - é obrigatório</br>
powerUnit - é obrigatório, valores aceitos são W, KW, MW</br>
active - é obrigatório</br>

3.	Person</br>
O objeto Pessoa tem os campos id, name, lastName, dateBirth, email, mobile, fiscalNumber, gender, active.</br></br>
•	<b>Validações</b></br>
name - mínimo 3 caracteres</br>
lastName - mínimo 3 caracteres</br>
dateBirth - é obrigatório, não aceita datas superior ao dia atual</br>
email – é obrigatório, verifica se a informação enviada é um email</br>
fiscalNumber - é obrigatório, verifica se é um CPF valido</br>
gender - é obrigatório, valores aceitos são M, F</br>
active - é obrigatório</br>

## Endpoints

O exemplo dos inputs e respostas estão na documentação criada no postman.</br>
Link documentação do Postman: [Doc. Upscale Energy](https://documenter.getpostman.com/view/2772269/2s93z6djFF)</br>
1.	Endereço</br>
•	GET - /address </br>
•	GET - /address/{id}</br>
•	POST - /address </br>
•	PUT - /address/{id}</br>
•	DELETE - /address/{id}</br>

2.	Eletrodoméstico</br>
•	GET - /appliance</br>
•	GET - /appliance/{id}</br>
•	POST - /appliance</br>
•	PUT - /appliance/{id}</br>
•	DELETE - /appliance/{id}</br>

3.	Pessoa</br>
•	GET - /person</br>
•	GET - /person/{id}</br>
•	POST - /person</br>
•	PUT - /person/{id}</br>
•	DELETE - /person/{id}</br>
