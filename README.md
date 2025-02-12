# Mia 📈💰

Mia é um aplicativo Android desenvolvido para auxiliar investidores na gestão de seus investimentos e na declaração do Imposto de Renda. O projeto segue a arquitetura Clean e está estruturado em módulos para melhor organização e escalabilidade.

## 📂 Estrutura do Projeto

O projeto está dividido nos seguintes módulos:

- **app**: Módulo principal que integra os demais módulos e contém a camada de apresentação.
- **designsystem**: Contém os componentes de UI reutilizáveis do projeto.
- **stocks**: Responsável por funcionalidades relacionadas a ações e investimentos.

Dentro de cada módulo, seguimos a seguinte estrutura de diretórios:

- **data**: Implementação da camada de dados, incluindo repositórios e fontes de dados.
- **di**: Configuração de injeção de dependências.
- **domain**: Contém os casos de uso e modelos de domínio.
- **extensions**: Extensões utilitárias para facilitar o desenvolvimento.
- **presenter**: Implementação da camada de apresentação utilizando Jetpack Compose.

## 🚀 Padrão de GitFlow e Commits

O projeto segue o **padrão de commit atômico** e utilizamos **Gitmoji** para identificar a finalidade de cada commit:

| Emoji         | Significado                                      |
|--------------|------------------------------------------------|
| ✨ `:sparkles:` | Implementação de novas features               |
| 🐛 `:bug:`    | Correção de bugs                              |
| 🚀 `:rocket:` | Releases de novas versões                     |
| 🔧 `:wrench:` | Configurações do projeto                      |
| 🔨 `:hammer:` | Ajustes de ferramentas externas (ex: CI/CD)   |
| 🧪 `:test_tube:` | Criação ou edição de testes                 |
| 📦 `:card_file_box:` | Documentação do projeto                 |
| 🎉 `:tada:`  | Início do projeto                              |

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Kotlin
- **UI**: Jetpack Compose
- **Injeção de Dependência**: Hilt
- **Arquitetura**: Clean Architecture
- **Gerenciamento de Estados**: Flow + ViewModel
- **Networking**: Ktor
- **Build System**: Gradle Kotlin DSL com version catalog

## 📌 Como Contribuir

1. Faça um fork do repositório.
2. Crie uma branch para sua feature (`git checkout -b feat/minha-feature`).
3. Realize as alterações necessárias e faça commits seguindo o padrão Gitmoji.
4. Envie um pull request para análise.

## 📄 Licença

Este projeto está licenciado sob a [Apache-2.0](LICENSE).