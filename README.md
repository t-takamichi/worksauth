# システム概要
- 社内共有サイトの認証サービス

# システム
- ID/PASS認証を実施する。
  - /auth/login
    - 認証が成功した場合は、
      - JWTを返す
    - 失敗
      - 失敗のレスポンスを返す
- JWTの値が正しいのか確認する
    - /auth/validation
      - 各APIにリクエスト時にJWTの値を確認するために使用する


## ID/PASSWORD認証の使用
- 認証が失敗した場合は、AuthErrorExceptionを発生させる。
- 認証が成功した場合には、JWTと期限とAuthIDを付与する
  - JWT作成する必要がある。
  - DBを# worksauth
