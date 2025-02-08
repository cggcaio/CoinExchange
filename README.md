# CoinExchange

CoinExchange é um aplicativo Android desenvolvido em Kotlin e Jetpack Compose, que permite a consulta de corretoras de
criptomoedas utilizando a API da CoinAPI.io.

## ✨ **Features**

- **Listagem de Corretoras**: Apresenta uma lista das corretores com informações relevantes, como nome, ID e volume de
  negociação nas últimas 24h.
- **Detalhes da Corretora**: Ao selecionar uma corretora, é possível visualizar detalhes adicionais sobre ela.

## 🎥 **Demonstração**

![Alt text](assets/presentation_app.gif)

## 🛠 Tecnologias Utilizadas

- **Linguagem**: ![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)
- **UI Framework**: ![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=android&logoColor=white)
- **Injeção de Dependência**: ![Hilt](https://img.shields.io/badge/Hilt-4285F4?style=for-the-badge&logo=android&logoColor=white)
- **Networking**: ![Retrofit](https://img.shields.io/badge/Retrofit-3E4348?style=for-the-badge&logo=square&logoColor=white)
- **Testes**: ![JUnit](https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white) ![Compose Testing](https://img.shields.io/badge/Compose%20Testing-4285F4?style=for-the-badge&logo=android&logoColor=white)
- **Arquitetura**: MVVM + Clean Architecture

## 🚀 **Como Executar o Projeto**

### Passos

1. **Clonar o repositório**
    ```bash
    git clone https://github.com/cggcaio/CoinExchange.git
    ```

2. **Abrir no Android Studio**
   - Abra o projeto na IDE e aguarde a sincronização das dependências.

3. **Configurar API Key**
   - Crie um arquivo `local.properties` na raiz do projeto.
   - Adicione sua chave da CoinAPI.io:
    ```properties
    COIN_API_KEY="sua_api_key_aqui"
    BASE_URL="rest.coinapi.io"
    ```
   - _Caso não tenha uma chave, acesse a [CoinAPI.io](https://docs.coinapi.io/?shell#list-all-exchanges-get) e crie uma conta._

4. **Rodar o aplicativo**
   - Após o Sync, execute um emulador ou conecte um dispositivo físico.
   - Execute o projeto pressionando no botão de "Run".

## 📌 **Estrutura do Projeto**

```
coinexchange/
│-- app/
|   |--core/               # Core do projeto (bases, components, utils, widgets...)
│   │-- exchange/          # Módulo de Corretoras 
│   │   │-- constants/     # Constantes
│   │   │-- data/          # Mappers, models, service
│   │   │-- di/            # Injeção de dependência do módulo
│   │   │-- domain/        # Models, repositories, usecases
│   │   │-- navigation/    # Navegação
│   │   │-- presentation/  # UI, viewmodels, previews
│   │-- network/           # Retrofit
│   │-- ui/                # Telas do aplicativo (Compose)
│   |   │-- theme/         # Temas e estilos visuais customizados
│-- build.gradle.kts       # Configuração do projeto
```

🎨 **Ícone do App**

![Alt text](assets/ic_256.png)

Desenvolvido com ❤️ por **Caio Gonçalves** 🤠.
