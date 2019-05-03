# ErasmusInfo
Trabalho para a aula de Programação Avançada.

A app possibilita ao utilizador, registar informação sobre o país e a(s) universidade(s), e o que pretende fazer em Erasmus.

Funcionalidades:
 
  - Registo de disciplinas a fazer na(s) universidade(s).
  - Registo da nota final das disciplinas feitas na(s) universidade(s).
  - Registo de informações do país importante. 
    - Contactos: Polícia, Hospital, etc
 
 Tabelas:
 
  - Perfil
    - Nome
    - Idade
  - Universidades
    - Nome
    - País
    - Localização
  - Contactos
    - Nome
    - Número
  - Disciplinas
    - Codigo
    - Nome
    - ECTs
    - Disciplina Equivalente
    - Nota
   
 Relações:
  
   - Perfil 1..M Contactos
   - Perfil 1..M Universidades
   - UniversidadeM 1..M Disciplinas
  
   <img src="mod_er.png" alt="Mod ER" height="" width=""> 
  
