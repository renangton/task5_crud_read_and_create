# 第5回講座課題 CRUD処理 (Read&Create)
## 概要
このプロジェクトはゲームのデータの新規登録及び検索をするレポジトリです

## 前提
- Java 11
- Spring Boot 2.6.3
- Docker 20.10.12

## 各レイヤの責務
### Controller
画面遷移の制御とServiceの呼び出しのみを担当する

### Service
業務処理の提供を担当する

### Mapper
CRUD処理を担当する

## Getting Started
`$ git clone https://github.com/renangton/task5_crud_read_and_create.git`

`$ cd task5_crud_read_and_create`

`$ docker-compose up -d`

`$ ./gradlew bootRun`

起動成功時のイメージ

![task5_gradlew](https://user-images.githubusercontent.com/97335620/154414840-af43072c-09a6-4d1a-8c6f-4c170b4a6836.png)

`http://localhost:8080/search` にアクセスすると検索画面が表示される

**検索画面**

![検索画面](https://user-images.githubusercontent.com/97335620/158062540-e48bf687-e93f-4670-a517-d20f383dd0a3.png)

- 検索条件のIDを指定しない場合、全件検索
  - 昇順か降順を選択し、選択に応じ表示する
- 検索条件のIDが入力されている場合、IDで1件検索
  - レコードが存在しない時、文字列で検索した時はエラー内容を表示する

**検索結果（昇順全件検索）**

![検索結果](https://user-images.githubusercontent.com/97335620/158062636-5865077a-5e4e-421d-a7fb-2da0ac568f03.png)

**GAME登録画面**

![登録画面](https://user-images.githubusercontent.com/97335620/158062752-3cedae6e-785f-43e2-b296-aa21b355579a.png)

- 各項目を入力及び選択し、登録する
  - 登録時に入力エラーがあった場合、登録が失敗し、どのエラーが発生したか表示する
  - プラットフォームを複数登録する場合は、「ctrl + クリック」で選択する

**登録成功時**

![登録成功](https://user-images.githubusercontent.com/97335620/158062779-5b1abaf7-0400-4d53-b11f-308f1fae140c.png)

**登録失敗時**

![登録失敗](https://user-images.githubusercontent.com/97335620/158062805-2cde9c67-21e2-4b01-b0f0-71afa121e1eb.png)

**PLATFORM登録画面**

![プラットフォーム登録画面](https://user-images.githubusercontent.com/97335620/158063227-70bc07ff-1f45-4445-af77-7309c0192679.png)

- プラットフォームを入力し、登録する
  - 登録時に入力エラー及び重複していた場合、登録が失敗し、どのエラーが発生したか表示する

（`./gradlew bootRun`コマンドで実行している場合）`ctrl + c`で終了する

お試し
