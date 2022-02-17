# 第5回講座課題 CRUD処理 (Read&Create)
## 概要
このプロジェクトはゲームのデータの新規登録及び検索をするレポジトリです

## 前提
- Java 11
- Spring Boot 2.6.3
- Docker 20.10.12

## Getting Started
`$ git clone https://github.com/renangton/task5_crud_read_and_create.git`

`$ cd task5_crud_read_and_create`

`$ docker-compose up -d`

`$ ./gradlew bootRun`

起動成功時のイメージ

![task5_gradlew](https://user-images.githubusercontent.com/97335620/154414840-af43072c-09a6-4d1a-8c6f-4c170b4a6836.png)

`http://loccalhost:8080/search`にアクセスすると検索画面が表示される

**検索画面**

![task5search](https://user-images.githubusercontent.com/97335620/154416093-2b440f58-1e16-4fff-b64d-f8e7f5db9b8b.png)

- 検索条件のIDがnullの場合、全件検索
  - 昇順か降順を選択し、選択に応じ表示する
- 検索条件のIDが入力されている場合、IDで1件検索
  - レコードが存在しない時、文字列で検索した時はエラー内容を表示する

**検索結果（昇順全件検索）**

![ascresult](https://user-images.githubusercontent.com/97335620/154419786-58dadc8a-2e2d-4eb2-abf2-1e3369dfc65e.png)

**登録画面**

![create](https://user-images.githubusercontent.com/97335620/154419986-7c892c2e-a7a1-4a6a-8799-75fd860a7225.png)

- 各項目を入力し、登録する
  - 登録時に入力エラーがあった場合、登録が失敗し、どのエラーが発生したか表示する

**登録成功時**

![createOK](https://user-images.githubusercontent.com/97335620/154424777-3a2eeebc-ef13-424d-93b4-d850e55defbb.png)

**登録失敗時**

![createNO](https://user-images.githubusercontent.com/97335620/154424877-79a23316-b401-4201-97d7-d6be50bae44d.png)

（`./gradlew bootRun`コマンドで実行している場合）`ctrl + c`で終了する
