# speedpersonapi
## api for speed bootcamp
### Neste estudo, aproveitei de um projeto apresentado em um curso sobre boas práticas e acrescentei correções na classe referente ao método put e melhor caracterizei as exceções.

* Melhoramento da requisição PUT.

Para o caso da atualização de um ente no cadastro, o código original do curso, como segue:
```
    public MessageResponseDTO putPerson(Long id, PersonDTO personDTO) throws PersonNotFoundException{
        personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        Person updatedPerson = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(updatedPerson);
        MessageResponseDTO messageResponse = createMessageResponse(savedPerson.getId(), "Person successfully updated with ID ");
        return messageResponse;
    }

```
não possibilita atualizar os dados da pessoa, com id específico e cpf válido, erro 500, e sim, cria um novo cadastro, caso os dados de atualização possuam cpf válido e diferente do cpf já cadastrado, o que aparenta ser um erro de código, que foge do resultado previsto.
Sendo assim, o escrevi como segue:

```
    public MessageResponseDTO putPerson(Long id, PersonDTO personDTO) throws PersonNotFoundException{
        verifyIfExists(id);
        Person personToUpdate = personRepository.getById(id);
        PersonDTO personDTOToUpdate = personMapper.toDTO(personToUpdate);
        personDTOToUpdate.setFirstName(personDTO.getFirstName());
        personDTOToUpdate.setLastName(personDTO.getLastName());
        personDTOToUpdate.setCpf(personDTO.getCpf());
        personDTOToUpdate.setBirthDate(personDTO.getBirthDate());
        personDTOToUpdate.setPhones(personDTO.getPhones());
        Person updatePerson = personMapper.toModel(personDTOToUpdate);
        Person update = personRepository.save(updatePerson);
        return createMessageResponse(update.getId(), "Update person with id ");
   
```
que, após popular com dados no formato JSON e cpf válidos, como exemplo:

```
{
        "id": 1,
        "firstName": "Ivã",
        "lastName": "Nazaré",
        "cpf": "-----------",
        "birthDate": "1978-08-15",
        "phones": [
            {
                "id": 1,
                "type": "MOBILE",
                "number": "(71)984191000"
            }
        ]
        }
    
```
obtem-se uma resposta válida, com a questões,

```
SELECT person.id, PERSON.FIRST_NAME, PHONE.NUMBER, phone.type FROM PERSON left JOIN PHONE ON PERSON.ID = PHONE.PERSON_ID;
select * from phone;

```
mostrando, assim, a atualização do dado cadastrado, como uma relação de uma pessoa para um tipo de telefone.
