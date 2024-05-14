[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[JAVA_BADGE]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[POSTGRES_BADGE]: https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white
[MAVEN_BADGE]: https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white

# Link Shortener 💻

![Spring][SPRING_BADGE]
![Java][JAVA_BADGE]
![Postgres][POSTGRES_BADGE]
![Apache Maven][MAVEN_BADGE]

An API developed to test knowledge and for that I created an API that is responsible for shortening links.

### Resum

The idea is to provide an endpoint that the user will consume and send the url they want to shorten, if 
it is a valid url the API will return a shortened url. The inspiration for this project was taken from the 
following [repository](https://github.com/backend-br/desafios/blob/master/url-shortener/PROBLEM.md).

## 🚀 Getting started

### Requirements

1. Java 21
2. Postgres Database
3. Maven

### Step by Step

1. Clone this repository.
```bash
git clone https://github.com/andregurgel/link-shortener.git
```

2. In your postgres database create a database called 'link-shortener'.
```postgresql
create database link-shortener
```

3. In 'application.properties' configure the database access credentials and choose the port on which your application will run.
4. In the GlobalProperties class, change the value of the url property to what you will use, default: "http://localhost:8080".
5. Okay, it looks like we're done there.

## 📍 API Endpoints

| route                              | description                                          
|------------------------------------|-----------------------------------------------------
| <kbd>POST /shorten-url</kbd>       | authenticate user into the api see [request details](#post-shorten-url)
| <kbd>GET /{shortenedUrlCode}</kbd> | retrieves user info see [response details](#get-shortened-url)

<h3 id="post-shorten-url">POST /shorten-url</h3>

**REQUEST**
```json
{
  "url": "https://url-to-short.com"
}
```

**RESPONSE**
```json
{
  "url": "{BACKEND_URL}/XYZAB" // "XYZAB" -> Is the {shortenedUrlCode}.
}
```

<h3 id="get-shortened-url">GET /{shortenedUrlCode}</h3>

**RESPONSE**
```text
The response to this request is a redirect to the url you shortened. (302 FOUND)
```

or

**RESPONSE ERROR**

```json
{
  "status": "NOT_FOUND",
  "message": "Error message for this case."
}
```

<h2 id="colab">🤝 Owner</h2>

I want to thank in advance all the people who were interested in the project and downloaded it to test it!

<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/32176559?v=4" width="100px;" alt="Andre Gurgel Profile Picture"/><br>
        <sub>
          <b>André Gurgel</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

