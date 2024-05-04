package com.postech.gestaodeenvio.service.integracao;

import com.google.gson.Gson;
import com.postech.gestaodeenvio.entities.Cotacao;
import com.postech.gestaodeenvio.entities.To;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Service
public class MelhorEnvioClient {

        private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0NTEwIiwianRpIjoiN2ZiYmI3MGZiMTFlMzM5OWE4NzgzZDJiZjMyNWM5MDdhYmU1NDU2ZjE4NjMwOTQwM2UyNDQ2YWU3OTQyMzYzOGM2ZDhhN2YxYzdjNDEzZjUiLCJpYXQiOjE3MTQ3OTg1NjQuODQ3NzY2LCJuYmYiOjE3MTQ3OTg1NjQuODQ3NzY5LCJleHAiOjE3MTczOTA1NjQuNzM2OTc3LCJzdWIiOiI5YmYwZjZmMy0wMzYxLTQ4OTQtODJiNC05MjQ4OWYxMzQ4NTkiLCJzY29wZXMiOlsiY2FydC1yZWFkIiwiY2FydC13cml0ZSIsImNvbXBhbmllcy1yZWFkIiwiY29tcGFuaWVzLXdyaXRlIiwiY291cG9ucy1yZWFkIiwiY291cG9ucy13cml0ZSIsIm5vdGlmaWNhdGlvbnMtcmVhZCIsIm9yZGVycy1yZWFkIiwicHJvZHVjdHMtcmVhZCIsInByb2R1Y3RzLXdyaXRlIiwicHVyY2hhc2VzLXJlYWQiLCJzaGlwcGluZy1jYWxjdWxhdGUiLCJzaGlwcGluZy1jYW5jZWwiLCJzaGlwcGluZy1jaGVja291dCIsInNoaXBwaW5nLWNvbXBhbmllcyIsInNoaXBwaW5nLWdlbmVyYXRlIiwic2hpcHBpbmctcHJldmlldyIsInNoaXBwaW5nLXByaW50Iiwic2hpcHBpbmctc2hhcmUiLCJzaGlwcGluZy10cmFja2luZyIsImVjb21tZXJjZS1zaGlwcGluZyIsInRyYW5zYWN0aW9ucy1yZWFkIiwidXNlcnMtcmVhZCIsInVzZXJzLXdyaXRlIl19.nCq9anPCaJBQYmArjYiqf6viZYpvi6BdUleF_0938_eKoG4aQJV1pl6BnX-hLbU_kAcAcgRbYDGdxZNm-UGwpk1obz_TR1Np8hQXDGrx06u_GNSCs3EbEPLw3KIoe4YkJtiDYUzJFbe3YchxqCHC-7ChMd5PFRwWKLYb7dGj3nfOl3e786cyhGaZfqCt0jS4NHozdCd-SXQdQmQUo84HN99j1sTj9F2Djfaw2wzGzBJlbjmbIrGY-7S0hoBh6WpuxXS-Odnejn7v3-Bf0-dlw6odPfJNeKTInUQj0qO7ouBxE1CJ2xvEElvdug94phjUa-YqvqZ3UJ7vSF4DhN4ILzMkz9fWqCT79j-qecU6Y7NbLuQT7NlhvPCk_eNzivX5TBtGTiSRc4_YD-3njaUer2KF4-YPfT-XxdtV0PgJCvhVL1nYqi1l6QlnyRzxnSTG0K9w2Yum50Od-eI57ukj48WAXzmmXpu4uNpNC0F5uGeWQvV4A2xCwg_JATot48Z5WI5d2D9QokXfiWlHiXRypm3DQaxWcApEBnKkW2JdXtPGfoRf6ed_B8pXY95i0EfkD-N1CSOZknpCN5OMI3uPuoo-OAzR0rFp1-oh-KkMQtIkD4HowkMyta2GuJwBKz1sJH9NkbCR7bY28k8wLIEnPd-ZOcNABPJIMGTUGZSNbbU";
        public void criarPedido() {

        }

        public ResponseEntity<?> calcularFrete(String toCep) throws IOException, InterruptedException {

            Gson gson = new Gson();
            String json = gson.toJson(new Cotacao(new To(toCep)));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://sandbox.melhorenvio.com.br/api/v2/me/shipment/calculate"))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .header("User-Agent", "pauloricardossfilho@gmail.com")
                    .method("POST", HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return ResponseEntity.ok(response.body());
        }


        public void rastrearPedido() {

        }



}
