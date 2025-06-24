{
	"info": {
		"_postman_id": "6e257045-8726-412e-9a7b-7bb1811a7d4f",
		"name": "Catsgram",
		"description": "Коллекция запросов и тестов для приложения **Catsgram**",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "2465623"
	},
	"item": [
		{
			"name": "Users",
			"item": [
				{
					"name": "Create USER",
					"event": [
						{
							"listen": "test",
							"script": {
								"exec": [
									"if(pm.response.code === 200) {",
									"    pm.test(\"Данные пользователя должны быть корректными\", function() {",
									"        const user = pm.response.json();",
									"        ",
									"        pm.expect(user).to.be.an('object')",
									"                .that.have.all.deep.keys('id', 'username', 'email', 'password', 'registrationDate');",
									"        pm.expect(user.id).to.be.ok;",
									"        pm.expect(user.registrationDate).to.be.ok;",
									"    });",
									"}"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"username\": \"{{$randomUserName}}\",\n    \"password\": \"{{$randomPassword}}\",\n    \"email\": \"{{$randomEmail}}\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{base-url}}/users",
							"host": [
								"{{base-url}}"
							],
							"path": [
								"users"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get Users",
					"event": [
						{
							"listen": "test",
							"script": {
								"exec": [
									"if(pm.response.code === 200) {",
									"    pm.test(\"Ответ должен соответствовать ожидаемому\", function () {",
									"        const body = pm.response.json();",
									"        pm.expect(body).to.be.an('array');",
									"",
									"        if(body.length !== 0) {",
									"            for(const user of body) {",
									"                pm.expect(user).to.be.an('object')",
									"                .have.all.deep.keys('id', 'username', 'email', 'password', 'registrationDate');",
									"                pm.expect(user.id).to.be.ok;",
									"                pm.expect(user.registrationDate).to.be.ok;",
									"            }",
									"        }",
									"    });",
									"}"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "{{base-url}}/users",
							"host": [
								"{{base-url}}"
							],
							"path": [
								"users"
							]
						}
					},
					"response": []
				},
				{
					"name": "Update User",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"id\": 1,\n    \"password\": \"{{$randomPassword}}\",\n    \"email\": \"{{$randomEmail}}\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{base-url}}/users",
							"host": [
								"{{base-url}}"
							],
							"path": [
								"users"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Posts",
			"item": [
				{
					"name": "Create POST",
					"event": [
						{
							"listen": "test",
							"script": {
								"exec": [
									"if(pm.response.code === 200) {",
									"    pm.test(\"Данные поста должны быть корректны\", function() {",
									"        const post = pm.response.json();",
									"        ",
									"        pm.expect(post).to.be.an('object')",
									"            .that.have.all.deep.keys('id', 'authorId', 'description', 'postDate');",
									"        pm.expect(post.id).to.be.ok;",
									"        pm.expect(post.postDate).to.be.ok;",
									"    });",
									"}"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"authorId\": 1,\n    \"description\": \"{{$randomLoremSentences}}\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{base-url}}/posts",
							"host": [
								"{{base-url}}"
							],
							"path": [
								"posts"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get Posts",
					"event": [
						{
							"listen": "test",
							"script": {
								"exec": [
									"if(pm.response.code === 200) {",
									"    pm.test(\"Ответ должен соответствовать ожидаемому\", function () {",
									"        const body = pm.response.json();",
									"        pm.expect(body).to.be.an('array');",
									"",
									"        if(body.length !== 0) {",
									"            for(const post of body) {",
									"                pm.expect(post).to.be.an('object')",
									"                    .that.has.all.deep.keys('id', 'authorId', 'description', 'postDate');",
									"                pm.expect(post.id).to.be.ok;",
									"                pm.expect(post.postDate).to.be.ok;",
									"            }",
									"        }",
									"    });",
									"}"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "{{base-url}}/posts",
							"host": [
								"{{base-url}}"
							],
							"path": [
								"posts"
							],
							"query": [
								{
									"key": "from",
									"value": "-1",
									"disabled": true
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Update Post",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"id\": 1,\n    \"authorId\": 1,\n    \"description\": \"{{$randomLoremSentences}}\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{base-url}}/posts",
							"host": [
								"{{base-url}}"
							],
							"path": [
								"posts"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "home",
			"item": [
				{
					"name": "Home Page",
					"event": [
						{
							"listen": "test",
							"script": {
								"exec": [
									"pm.test(\"Эндпоинт домашней страницы должен возвращать правильный текст\", function () {\r",
									"    pm.expect(pm.response.text()).to.include(\"Приветствуем вас, в приложении Котограм\");\r",
									"});"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "GET",
						"header": [
							{
								"key": "Accept",
								"value": "*/*"
							}
						],
						"url": {
							"raw": "{{base-url}}/home",
							"host": [
								"{{base-url}}"
							],
							"path": [
								"home"
							]
						}
					},
					"response": [
						{
							"name": "OK",
							"originalRequest": {
								"method": "GET",
								"header": [],
								"url": {
									"raw": "{{baseUrl}}/home",
									"host": [
										"{{baseUrl}}"
									],
									"path": [
										"home"
									]
								}
							},
							"status": "OK",
							"code": 200,
							"_postman_previewlanguage": "text",
							"header": [
								{
									"key": "Content-Type",
									"value": "*/*"
								}
							],
							"cookie": [],
							"body": "dolor irure esse"
						}
					]
				}
			]
		}
	],
	"event": [
		{
			"listen": "prerequest",
			"script": {
				"type": "text/javascript",
				"exec": [
					""
				]
			}
		},
		{
			"listen": "test",
			"script": {
				"type": "text/javascript",
				"exec": [
					"pm.test('Сервер должен успешно обработать запрос', () => {",
					"    pm.expect(pm.response.code, 'Код статуса должен быть 200').to.be.equal(200);",
					"    pm.response.to.have.header(\"Content-Type\");",
					"    const header = pm.response.headers.get('Content-Type');",
					"    if(header.toLowerCase().includes('application/json')) {",
					"        pm.expect(pm.response.json(), 'Тело ответа не должно быть пустым').is.not.empty;",
					"    }",
					"});",
					"",
					"if(['POST', 'PUT'].includes(pm.request.method)) {",
					"    pm.test('Ответ должен содержать данные указанные в запросе', () => {",
					"        let reqBody = pm.request.body.toJSON;",
					"        let respBody = pm.response.json();",
					"        pm.expect(respBody, 'Json должен содержать данные переданные в запросе').to.deep.include(reqBody);",
					"    });",
					"}"
				]
			}
		}
	],
	"variable": [
		{
			"key": "base-url",
			"value": "http://localhost:8080",
			"type": "string"
		}
	]
}