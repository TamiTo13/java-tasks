package bg.sofia.uni.fmi.mjt.cookingcompass.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipeRetrieverTest {
    @Mock
    public HttpReceiver receiver;

    @InjectMocks
    private RequestMaker requestMaker = new RequestMaker(
            List.of("mock"),
            List.of("Breakfast"),
            List.of("vegan"),
            receiver);

    @Test
    void testRetrieve() {
        when(receiver.send(requestMaker.getRequest(),requestMaker.getClient())).
                thenReturn(fileContents);

        RecipeRetriever retriever = new RecipeRetriever(requestMaker);
        retriever.retrieve();

    }

    String fileContents = "{\n" +
            "    \"from\": 1,\n" +
            "    \"to\": 1,\n" +
            "    \"count\": 1,\n" +
            "    \"_links\": \"{}\",\n" +
            "    \"hits\": [\n" +
            "        {\n" +
            "            \"recipe\": {\n" +
            "                \"uri\": \"http://www.edamam.com/ontologies/edamam.owl#recipe_e82cabb48ac803c29727dfa018a1a334\",\n" +
            "                \"label\": \"Moroccan Spiced Chicken Skewers\",\n" +
            "                \"image\": \"https://edamam-product-images.s3.amazonaws.com/web-img/58c/58cf5a4459efbfb001c20a444cb876a3.jpg?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFoaCXVzLWVhc3QtMSJGMEQCICvsB6L2joE2B%2BtIJvXGHeCSxgnAfTaPfln7GigMHnCcAiBC8aRZvVUg8by%2FvAQreQKuhXuTnV0%2B1qmTg9XBO%2BKWMyq5BQgSEAAaDDE4NzAxNzE1MDk4NiIMpi0WdX2DbIb3cUQbKpYFWFTpptGWQD5M6WQYDY1ndHBEqH2YPmUxbx9eCsKyQzQiuqO1Ub7DGa6qoSHYxjPeDHbAw9dmEAVfUox4PzvnGlN4rF0d8i7FXd8QJHnPIIkoSRIrDM13VSvF%2FtfkTz2MPTnIZP3L31glyJC5i%2FokcjKoE0diMLZzgMZoABepJuVFfBgx5Pz%2Fjg2hvmSMLHMV1pItUp%2BE6zGDbTXMgC90hcLCkZe24jWssoRMHgIe0xaFqDqDGVxDMhY9zv%2BuovwQanbYwhcFqqc3XrqwfjmIchhPismqBC19wlkKcW%2F9Bp4iL1b6JGhcuVvrC3%2FQ1F5yhH99jZ0KulxzyEp%2Bh4qsDGoHi7AbFJWhJtReEY13qzySxPFw0iryrv%2FNR0aQAcGik06umD9ijZGcpcMuGZn35bmvJqDwkPgS2rthJsbUueIgWFGNxFBI%2B45ql12%2BLxubtoU777ySPnzOOc184IF2yW0xFyYHa%2F%2BeJyo7PLObmteycJ7v8SnkAQnbj8JaZkNXBsLZbUmoqJOVfAI0xuQathXrOkudVEysG7xbevKfVNIdbIiTvZ3rWaqsVNZ27qsWQHEGnQx5zJ%2FcQK6EV2a8M8cQAP7klYQuu%2BtRfvUwCVOrnTEk67pTRSCpObFUBJ3KKhauBINfIPbsrZTey0gKHXtTedu0Q7A6cmx8kKkpSvG30KFtxqqvS6kBNA7LwHvjRLgWH%2Fdr7o%2Bp7hxUPlaUu35XYn2ipTny8TFFo8rW1pdtsP%2Fkh4yhGdeD9gEFHTXAtvnB83R37NZsokMhBVNptGYVAddmXYIrX4jiyzIK3RzsRUE8t%2FrgVmyLjeu9ZgSa8omQxO5XpPwDDl2Pu1Gr2i9IviNjljvCVKSfEuFJM3WuOy0cybEwoJ%2BurQY6sgEk3hN1ETQguoW5ya9fsDIbhLnS6LFRoreJO99u3Ymg9AzT%2FMuADE7R%2BJPu9POfDbzpMxqYUkISLHdNOHOCVUV3nfEKoS4r%2FhxQjtzRCmWa90n567EqwcP5PKP9MTDPuXCHqrHgGw%2BfyZiEbVOX3Ie2D%2FKmcKOnJLztUYFCFZThyznPOk43Qk6IWlqkJZCGMKoZNblMWWJahf5CU9NhdWRiCJJUr5a9EO6sZZpmiiWgxdWG&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20240120T104330Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIASXCYXIIFDJK5PJZI%2F20240120%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=6961d24a813dcd321fcbc2529fb0e79ca8c0e9aba5ab9550dded981fc463b877\",\n" +
            "                \"images\": {\n" +
            "                    \"THUMBNAIL\": {\n" +
            "                        \"url\": \"https://edamam-product-images.s3.amazonaws.com/web-img/58c/58cf5a4459efbfb001c20a444cb876a3-s.jpg?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFoaCXVzLWVhc3QtMSJGMEQCICvsB6L2joE2B%2BtIJvXGHeCSxgnAfTaPfln7GigMHnCcAiBC8aRZvVUg8by%2FvAQreQKuhXuTnV0%2B1qmTg9XBO%2BKWMyq5BQgSEAAaDDE4NzAxNzE1MDk4NiIMpi0WdX2DbIb3cUQbKpYFWFTpptGWQD5M6WQYDY1ndHBEqH2YPmUxbx9eCsKyQzQiuqO1Ub7DGa6qoSHYxjPeDHbAw9dmEAVfUox4PzvnGlN4rF0d8i7FXd8QJHnPIIkoSRIrDM13VSvF%2FtfkTz2MPTnIZP3L31glyJC5i%2FokcjKoE0diMLZzgMZoABepJuVFfBgx5Pz%2Fjg2hvmSMLHMV1pItUp%2BE6zGDbTXMgC90hcLCkZe24jWssoRMHgIe0xaFqDqDGVxDMhY9zv%2BuovwQanbYwhcFqqc3XrqwfjmIchhPismqBC19wlkKcW%2F9Bp4iL1b6JGhcuVvrC3%2FQ1F5yhH99jZ0KulxzyEp%2Bh4qsDGoHi7AbFJWhJtReEY13qzySxPFw0iryrv%2FNR0aQAcGik06umD9ijZGcpcMuGZn35bmvJqDwkPgS2rthJsbUueIgWFGNxFBI%2B45ql12%2BLxubtoU777ySPnzOOc184IF2yW0xFyYHa%2F%2BeJyo7PLObmteycJ7v8SnkAQnbj8JaZkNXBsLZbUmoqJOVfAI0xuQathXrOkudVEysG7xbevKfVNIdbIiTvZ3rWaqsVNZ27qsWQHEGnQx5zJ%2FcQK6EV2a8M8cQAP7klYQuu%2BtRfvUwCVOrnTEk67pTRSCpObFUBJ3KKhauBINfIPbsrZTey0gKHXtTedu0Q7A6cmx8kKkpSvG30KFtxqqvS6kBNA7LwHvjRLgWH%2Fdr7o%2Bp7hxUPlaUu35XYn2ipTny8TFFo8rW1pdtsP%2Fkh4yhGdeD9gEFHTXAtvnB83R37NZsokMhBVNptGYVAddmXYIrX4jiyzIK3RzsRUE8t%2FrgVmyLjeu9ZgSa8omQxO5XpPwDDl2Pu1Gr2i9IviNjljvCVKSfEuFJM3WuOy0cybEwoJ%2BurQY6sgEk3hN1ETQguoW5ya9fsDIbhLnS6LFRoreJO99u3Ymg9AzT%2FMuADE7R%2BJPu9POfDbzpMxqYUkISLHdNOHOCVUV3nfEKoS4r%2FhxQjtzRCmWa90n567EqwcP5PKP9MTDPuXCHqrHgGw%2BfyZiEbVOX3Ie2D%2FKmcKOnJLztUYFCFZThyznPOk43Qk6IWlqkJZCGMKoZNblMWWJahf5CU9NhdWRiCJJUr5a9EO6sZZpmiiWgxdWG&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20240120T104330Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIASXCYXIIFDJK5PJZI%2F20240120%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=020b15f14147ee346bf2f475da6301d4c2ac9f7f64deece16184181cf746b7ae\",\n" +
            "                        \"width\": 100,\n" +
            "                        \"height\": 100\n" +
            "                    },\n" +
            "                    \"SMALL\": {\n" +
            "                        \"url\": \"https://edamam-product-images.s3.amazonaws.com/web-img/58c/58cf5a4459efbfb001c20a444cb876a3-m.jpg?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFoaCXVzLWVhc3QtMSJGMEQCICvsB6L2joE2B%2BtIJvXGHeCSxgnAfTaPfln7GigMHnCcAiBC8aRZvVUg8by%2FvAQreQKuhXuTnV0%2B1qmTg9XBO%2BKWMyq5BQgSEAAaDDE4NzAxNzE1MDk4NiIMpi0WdX2DbIb3cUQbKpYFWFTpptGWQD5M6WQYDY1ndHBEqH2YPmUxbx9eCsKyQzQiuqO1Ub7DGa6qoSHYxjPeDHbAw9dmEAVfUox4PzvnGlN4rF0d8i7FXd8QJHnPIIkoSRIrDM13VSvF%2FtfkTz2MPTnIZP3L31glyJC5i%2FokcjKoE0diMLZzgMZoABepJuVFfBgx5Pz%2Fjg2hvmSMLHMV1pItUp%2BE6zGDbTXMgC90hcLCkZe24jWssoRMHgIe0xaFqDqDGVxDMhY9zv%2BuovwQanbYwhcFqqc3XrqwfjmIchhPismqBC19wlkKcW%2F9Bp4iL1b6JGhcuVvrC3%2FQ1F5yhH99jZ0KulxzyEp%2Bh4qsDGoHi7AbFJWhJtReEY13qzySxPFw0iryrv%2FNR0aQAcGik06umD9ijZGcpcMuGZn35bmvJqDwkPgS2rthJsbUueIgWFGNxFBI%2B45ql12%2BLxubtoU777ySPnzOOc184IF2yW0xFyYHa%2F%2BeJyo7PLObmteycJ7v8SnkAQnbj8JaZkNXBsLZbUmoqJOVfAI0xuQathXrOkudVEysG7xbevKfVNIdbIiTvZ3rWaqsVNZ27qsWQHEGnQx5zJ%2FcQK6EV2a8M8cQAP7klYQuu%2BtRfvUwCVOrnTEk67pTRSCpObFUBJ3KKhauBINfIPbsrZTey0gKHXtTedu0Q7A6cmx8kKkpSvG30KFtxqqvS6kBNA7LwHvjRLgWH%2Fdr7o%2Bp7hxUPlaUu35XYn2ipTny8TFFo8rW1pdtsP%2Fkh4yhGdeD9gEFHTXAtvnB83R37NZsokMhBVNptGYVAddmXYIrX4jiyzIK3RzsRUE8t%2FrgVmyLjeu9ZgSa8omQxO5XpPwDDl2Pu1Gr2i9IviNjljvCVKSfEuFJM3WuOy0cybEwoJ%2BurQY6sgEk3hN1ETQguoW5ya9fsDIbhLnS6LFRoreJO99u3Ymg9AzT%2FMuADE7R%2BJPu9POfDbzpMxqYUkISLHdNOHOCVUV3nfEKoS4r%2FhxQjtzRCmWa90n567EqwcP5PKP9MTDPuXCHqrHgGw%2BfyZiEbVOX3Ie2D%2FKmcKOnJLztUYFCFZThyznPOk43Qk6IWlqkJZCGMKoZNblMWWJahf5CU9NhdWRiCJJUr5a9EO6sZZpmiiWgxdWG&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20240120T104330Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIASXCYXIIFDJK5PJZI%2F20240120%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=33199ffa26acc94cfc5ac15d8001ff03b6f5a496e77d8c67a9f67fa747690438\",\n" +
            "                        \"width\": 200,\n" +
            "                        \"height\": 200\n" +
            "                    },\n" +
            "                    \"REGULAR\": {\n" +
            "                        \"url\": \"https://edamam-product-images.s3.amazonaws.com/web-img/58c/58cf5a4459efbfb001c20a444cb876a3.jpg?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFoaCXVzLWVhc3QtMSJGMEQCICvsB6L2joE2B%2BtIJvXGHeCSxgnAfTaPfln7GigMHnCcAiBC8aRZvVUg8by%2FvAQreQKuhXuTnV0%2B1qmTg9XBO%2BKWMyq5BQgSEAAaDDE4NzAxNzE1MDk4NiIMpi0WdX2DbIb3cUQbKpYFWFTpptGWQD5M6WQYDY1ndHBEqH2YPmUxbx9eCsKyQzQiuqO1Ub7DGa6qoSHYxjPeDHbAw9dmEAVfUox4PzvnGlN4rF0d8i7FXd8QJHnPIIkoSRIrDM13VSvF%2FtfkTz2MPTnIZP3L31glyJC5i%2FokcjKoE0diMLZzgMZoABepJuVFfBgx5Pz%2Fjg2hvmSMLHMV1pItUp%2BE6zGDbTXMgC90hcLCkZe24jWssoRMHgIe0xaFqDqDGVxDMhY9zv%2BuovwQanbYwhcFqqc3XrqwfjmIchhPismqBC19wlkKcW%2F9Bp4iL1b6JGhcuVvrC3%2FQ1F5yhH99jZ0KulxzyEp%2Bh4qsDGoHi7AbFJWhJtReEY13qzySxPFw0iryrv%2FNR0aQAcGik06umD9ijZGcpcMuGZn35bmvJqDwkPgS2rthJsbUueIgWFGNxFBI%2B45ql12%2BLxubtoU777ySPnzOOc184IF2yW0xFyYHa%2F%2BeJyo7PLObmteycJ7v8SnkAQnbj8JaZkNXBsLZbUmoqJOVfAI0xuQathXrOkudVEysG7xbevKfVNIdbIiTvZ3rWaqsVNZ27qsWQHEGnQx5zJ%2FcQK6EV2a8M8cQAP7klYQuu%2BtRfvUwCVOrnTEk67pTRSCpObFUBJ3KKhauBINfIPbsrZTey0gKHXtTedu0Q7A6cmx8kKkpSvG30KFtxqqvS6kBNA7LwHvjRLgWH%2Fdr7o%2Bp7hxUPlaUu35XYn2ipTny8TFFo8rW1pdtsP%2Fkh4yhGdeD9gEFHTXAtvnB83R37NZsokMhBVNptGYVAddmXYIrX4jiyzIK3RzsRUE8t%2FrgVmyLjeu9ZgSa8omQxO5XpPwDDl2Pu1Gr2i9IviNjljvCVKSfEuFJM3WuOy0cybEwoJ%2BurQY6sgEk3hN1ETQguoW5ya9fsDIbhLnS6LFRoreJO99u3Ymg9AzT%2FMuADE7R%2BJPu9POfDbzpMxqYUkISLHdNOHOCVUV3nfEKoS4r%2FhxQjtzRCmWa90n567EqwcP5PKP9MTDPuXCHqrHgGw%2BfyZiEbVOX3Ie2D%2FKmcKOnJLztUYFCFZThyznPOk43Qk6IWlqkJZCGMKoZNblMWWJahf5CU9NhdWRiCJJUr5a9EO6sZZpmiiWgxdWG&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20240120T104330Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIASXCYXIIFDJK5PJZI%2F20240120%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=6961d24a813dcd321fcbc2529fb0e79ca8c0e9aba5ab9550dded981fc463b877\",\n" +
            "                        \"width\": 300,\n" +
            "                        \"height\": 300\n" +
            "                    }\n" +
            "                },\n" +
            "                \"source\": \"eatsmarter.com\",\n" +
            "                \"url\": \"https://eatsmarter.com/recipes/moroccan-spiced-chicken-skewers\",\n" +
            "                \"shareAs\": \"http://www.edamam.com/recipe/moroccan-spiced-chicken-skewers-e82cabb48ac803c29727dfa018a1a334/tomato+chicken+onion/alcohol-free/dairy-free/DASH/egg-free\",\n" +
            "                \"yield\": 2,\n" +
            "                \"dietLabels\": [\n" +
            "                    \"High-Fiber\"\n" +
            "                ],\n" +
            "                \"healthLabels\": [\n" +
            "                    \"Paleo\",\n" +
            "                    \"Mediterranean\",\n" +
            "                    \"DASH\",\n" +
            "                    \"Dairy-Free\",\n" +
            "                    \"Gluten-Free\",\n" +
            "                    \"Wheat-Free\",\n" +
            "                    \"Egg-Free\",\n" +
            "                    \"Peanut-Free\",\n" +
            "                    \"Tree-Nut-Free\",\n" +
            "                    \"Soy-Free\",\n" +
            "                    \"Fish-Free\",\n" +
            "                    \"Shellfish-Free\",\n" +
            "                    \"Pork-Free\",\n" +
            "                    \"Red-Meat-Free\",\n" +
            "                    \"Crustacean-Free\",\n" +
            "                    \"Celery-Free\",\n" +
            "                    \"Mustard-Free\",\n" +
            "                    \"Sesame-Free\",\n" +
            "                    \"Lupine-Free\",\n" +
            "                    \"Mollusk-Free\",\n" +
            "                    \"Alcohol-Free\",\n" +
            "                    \"Sulfite-Free\",\n" +
            "                    \"Kosher\"\n" +
            "                ],\n" +
            "                \"cautions\": [\n" +
            "                    \"Sulfites\"\n" +
            "                ],\n" +
            "                \"ingredientLines\": [\n" +
            "                    \"7 ounces Chicken breast\",\n" +
            "                    \"1 red Onion\",\n" +
            "                    \"1 Garlic clove\",\n" +
            "                    \"3 Oranges\",\n" +
            "                    \"2 sprigs Thyme\",\n" +
            "                    \"1 teaspoon ground paprika\",\n" +
            "                    \"1 pinch Cayenne pepper\",\n" +
            "                    \"1 pinch Cinnamon\",\n" +
            "                    \"Salt\",\n" +
            "                    \"Pepper\",\n" +
            "                    \"½ bunch Arugula (about 40 grams)\",\n" +
            "                    \"4 ounces Cherry tomatoes\",\n" +
            "                    \"2 tablespoons Olive oil\"\n" +
            "                ],\n" +
            "                \"ingredients\": [\n" +
            "                    {\n" +
            "                        \"text\": \"7 ounces Chicken breast\",\n" +
            "                        \"quantity\": 7,\n" +
            "                        \"measure\": \"ounce\",\n" +
            "                        \"food\": \"Chicken breast\",\n" +
            "                        \"weight\": 198.44666187500002,\n" +
            "                        \"foodCategory\": \"Poultry\",\n" +
            "                        \"foodId\": \"food_bdrxu94aj3x2djbpur8dhagfhkcn\",\n" +
            "                        \"image\": \"https://www.edamam.com/food-img/da5/da510379d3650787338ca16fb69f4c94.jpg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"text\": \"1 red Onion\",\n" +
            "                        \"quantity\": 1,\n" +
            "                        \"measure\": \"<unit>\",\n" +
            "                        \"food\": \"red Onion\",\n" +
            "                        \"weight\": 125,\n" +
            "                        \"foodCategory\": \"vegetables\",\n" +
            "                        \"foodId\": \"food_bmrvi4ob4binw9a5m7l07amlfcoy\",\n" +
            "                        \"image\": \"https://www.edamam.com/food-img/205/205e6bf2399b85d34741892ef91cc603.jpg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"text\": \"1 Garlic clove\",\n" +
            "                        \"quantity\": 1,\n" +
            "                        \"measure\": \"clove\",\n" +
            "                        \"food\": \"Garlic\",\n" +
            "                        \"weight\": 3,\n" +
            "                        \"foodCategory\": \"vegetables\",\n" +
            "                        \"foodId\": \"food_avtcmx6bgjv1jvay6s6stan8dnyp\",\n" +
            "                        \"image\": \"https://www.edamam.com/food-img/6ee/6ee142951f48aaf94f4312409f8d133d.jpg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"text\": \"3 Oranges\",\n" +
            "                        \"quantity\": 3,\n" +
            "                        \"measure\": \"<unit>\",\n" +
            "                        \"food\": \"Oranges\",\n" +
            "                        \"weight\": 393,\n" +
            "                        \"foodCategory\": \"fruit\",\n" +
            "                        \"foodId\": \"food_b0bnl8oayiqhs2at63ifxbm6i3o3\",\n" +
            "                        \"image\": \"https://www.edamam.com/food-img/8ea/8ea264a802d6e643c1a340a77863c6ef.jpg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"text\": \"2 sprigs Thyme\",\n" +
            "                        \"quantity\": 2,\n" +
            "                        \"measure\": \"sprig\",\n" +
            "                        \"food\": \"Thyme\",\n" +
            "                        \"weight\": 6,\n" +
            "                        \"foodCategory\": \"Condiments and sauces\",\n" +
            "                        \"foodId\": \"food_b3o3cj7a5gskecb0ufphtadnbfqb\",\n" +
            "                        \"image\": \"https://www.edamam.com/food-img/3e7/3e7cf3c8d767a90b906447f5e74059f7.jpg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"text\": \"1 teaspoon ground paprika\",\n" +
            "                        \"quantity\": 1,\n" +
            "                        \"measure\": \"teaspoon\",\n" +
            "                        \"food\": \"paprika\",\n" +
            "                        \"weight\": 2.3,\n" +
            "                        \"foodCategory\": \"Condiments and sauces\",\n" +
            "                        \"foodId\": \"food_a9dpcnjb883g67b3lq82ca0421ql\",\n" +
            "                        \"image\": \"https://www.edamam.com/food-img/474/474d63763b9d8b9da98c5f43a114648c.jpg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"text\": \"1 pinch Cayenne pepper\",\n" +
            "                        \"quantity\": 1,\n" +
            "                        \"measure\": \"pinch\",\n" +
            "                        \"food\": \"Cayenne pepper\",\n" +
            "                        \"weight\": 0.110416666808545,\n" +
            "                        \"foodCategory\": \"Condiments and sauces\",\n" +
            "                        \"foodId\": \"food_a8iooz3aris8gba605l07brngnrx\",\n" +
            "                        \"image\": \"https://www.edamam.com/food-img/374/3742b9434a0fb66a45e0dd6d227ba669.jpg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"text\": \"1 pinch Cinnamon\",\n" +
            "                        \"quantity\": 1,\n" +
            "                        \"measure\": \"pinch\",\n" +
            "                        \"food\": \"Cinnamon\",\n" +
            "                        \"weight\": 0.16250000020880156,\n" +
            "                        \"foodCategory\": \"Condiments and sauces\",\n" +
            "                        \"foodId\": \"food_atjxtznauw5zabaixm24xa787onz\",\n" +
            "                        \"image\": \"https://www.edamam.com/food-img/d4d/d4daa18b92c596a1c99c08537c38e65b.jpg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"text\": \"Salt\",\n" +
            "                        \"quantity\": 0,\n" +
            "                        \"measure\": null,\n" +
            "                        \"food\": \"Salt\",\n" +
            "                        \"weight\": 5.4505060262521035,\n" +
            "                        \"foodCategory\": \"Condiments and sauces\",\n" +
            "                        \"foodId\": \"food_btxz81db72hwbra2pncvebzzzum9\",\n" +
            "                        \"image\": \"https://www.edamam.com/food-img/694/6943ea510918c6025795e8dc6e6eaaeb.jpg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"text\": \"Pepper\",\n" +
            "                        \"quantity\": 0,\n" +
            "                        \"measure\": null,\n" +
            "                        \"food\": \"Pepper\",\n" +
            "                        \"weight\": 2.7252530131260517,\n" +
            "                        \"foodCategory\": \"Condiments and sauces\",\n" +
            "                        \"foodId\": \"food_b6ywzluaaxv02wad7s1r9ag4py89\",\n" +
            "                        \"image\": \"https://www.edamam.com/food-img/c6e/c6e5c3bd8d3bc15175d9766971a4d1b2.jpg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"text\": \"½ bunch Arugula (about 40 grams)\",\n" +
            "                        \"quantity\": 40,\n" +
            "                        \"measure\": \"gram\",\n" +
            "                        \"food\": \"Arugula\",\n" +
            "                        \"weight\": 40,\n" +
            "                        \"foodCategory\": \"vegetables\",\n" +
            "                        \"foodId\": \"food_a8z1kzrawhksuzav2g1irb6zf4zc\",\n" +
            "                        \"image\": \"https://www.edamam.com/food-img/fa7/fa761a311efc0ec361a5eba4ed71870e.jpeg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"text\": \"4 ounces Cherry tomatoes\",\n" +
            "                        \"quantity\": 4,\n" +
            "                        \"measure\": \"ounce\",\n" +
            "                        \"food\": \"Cherry tomatoes\",\n" +
            "                        \"weight\": 113.3980925,\n" +
            "                        \"foodCategory\": \"vegetables\",\n" +
            "                        \"foodId\": \"food_a30b0hpbvavginafe0tocbf6ymje\",\n" +
            "                        \"image\": \"https://www.edamam.com/food-img/23e/23e727a14f1035bdc2733bb0477efbd2.jpg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"text\": \"2 tablespoons Olive oil\",\n" +
            "                        \"quantity\": 2,\n" +
            "                        \"measure\": \"tablespoon\",\n" +
            "                        \"food\": \"Olive oil\",\n" +
            "                        \"weight\": 27,\n" +
            "                        \"foodCategory\": \"Oils\",\n" +
            "                        \"foodId\": \"food_b1d1icuad3iktrbqby0hiagafaz7\",\n" +
            "                        \"image\": \"https://www.edamam.com/food-img/4d6/4d651eaa8a353647746290c7a9b29d84.jpg\"\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"calories\": 766.5465359639134,\n" +
            "                \"totalCO2Emissions\": 2626.1168599799093,\n" +
            "                \"co2EmissionsClass\": \"F\",\n" +
            "                \"totalWeight\": 916.3087109596801,\n" +
            "                \"totalTime\": 60,\n" +
            "                \"cuisineType\": [\n" +
            "                    \"middle eastern\"\n" +
            "                ],\n" +
            "                \"mealType\": [\n" +
            "                    \"breakfast\"\n" +
            "                ],\n" +
            "                \"dishType\": [\n" +
            "                    \"main course\"\n" +
            "                ],\n" +
            "                \"totalNutrients\": {\n" +
            "                    \"ENERC_KCAL\": {\n" +
            "                        \"label\": \"Energy\",\n" +
            "                        \"quantity\": 766.5465359639134,\n" +
            "                        \"unit\": \"kcal\"\n" +
            "                    },\n" +
            "                    \"FAT\": {\n" +
            "                        \"label\": \"Fat\",\n" +
            "                        \"quantity\": 33.80915905771338,\n" +
            "                        \"unit\": \"g\"\n" +
            "                    },\n" +
            "                    \"FASAT\": {\n" +
            "                        \"label\": \"Saturated\",\n" +
            "                        \"quantity\": 5.142807397477381,\n" +
            "                        \"unit\": \"g\"\n" +
            "                    },\n" +
            "                    \"FATRN\": {\n" +
            "                        \"label\": \"Trans\",\n" +
            "                        \"quantity\": 0.013891266331250002,\n" +
            "                        \"unit\": \"g\"\n" +
            "                    },\n" +
            "                    \"FAMS\": {\n" +
            "                        \"label\": \"Monounsaturated\",\n" +
            "                        \"quantity\": 21.3065567370985,\n" +
            "                        \"unit\": \"g\"\n" +
            "                    },\n" +
            "                    \"FAPU\": {\n" +
            "                        \"label\": \"Polyunsaturated\",\n" +
            "                        \"quantity\": 4.272284663208016,\n" +
            "                        \"unit\": \"g\"\n" +
            "                    },\n" +
            "                    \"CHOCDF\": {\n" +
            "                        \"label\": \"Carbs\",\n" +
            "                        \"quantity\": 69.5568185602326,\n" +
            "                        \"unit\": \"g\"\n" +
            "                    },\n" +
            "                    \"CHOCDF.net\": {\n" +
            "                        \"label\": \"Carbohydrates (net)\",\n" +
            "                        \"quantity\": 53.48753160442891,\n" +
            "                        \"unit\": \"g\"\n" +
            "                    },\n" +
            "                    \"FIBTG\": {\n" +
            "                        \"label\": \"Fiber\",\n" +
            "                        \"quantity\": 16.06928695580369,\n" +
            "                        \"unit\": \"g\"\n" +
            "                    },\n" +
            "                    \"SUGAR\": {\n" +
            "                        \"label\": \"Sugars\",\n" +
            "                        \"quantity\": 46.14711061871982,\n" +
            "                        \"unit\": \"g\"\n" +
            "                    },\n" +
            "                    \"PROCNT\": {\n" +
            "                        \"label\": \"Protein\",\n" +
            "                        \"quantity\": 52.901462199265474,\n" +
            "                        \"unit\": \"g\"\n" +
            "                    },\n" +
            "                    \"CHOLE\": {\n" +
            "                        \"label\": \"Cholesterol\",\n" +
            "                        \"quantity\": 144.86606316875,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    \"NA\": {\n" +
            "                        \"label\": \"Sodium\",\n" +
            "                        \"quantity\": 2118.8446470317544,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    \"CA\": {\n" +
            "                        \"label\": \"Calcium\",\n" +
            "                        \"quantity\": 321.580218717952,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    \"MG\": {\n" +
            "                        \"label\": \"Magnesium\",\n" +
            "                        \"quantity\": 158.0600293551652,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    \"K\": {\n" +
            "                        \"label\": \"Potassium\",\n" +
            "                        \"quantity\": 2113.854207918191,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    \"FE\": {\n" +
            "                        \"label\": \"Iron\",\n" +
            "                        \"quantity\": 4.3152291630754505,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    \"ZN\": {\n" +
            "                        \"label\": \"Zinc\",\n" +
            "                        \"quantity\": 2.5041124391014105,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    \"P\": {\n" +
            "                        \"label\": \"Phosphorus\",\n" +
            "                        \"quantity\": 584.8823525883718,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    \"VITA_RAE\": {\n" +
            "                        \"label\": \"Vitamin A\",\n" +
            "                        \"quantity\": 230.2342584019431,\n" +
            "                        \"unit\": \"µg\"\n" +
            "                    },\n" +
            "                    \"VITC\": {\n" +
            "                        \"label\": \"Vitamin C\",\n" +
            "                        \"quantity\": 250.5087720059497,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    \"THIA\": {\n" +
            "                        \"label\": \"Thiamin (B1)\",\n" +
            "                        \"quantity\": 0.6653183463088541,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    \"RIBF\": {\n" +
            "                        \"label\": \"Riboflavin (B2)\",\n" +
            "                        \"quantity\": 0.6639830386854331,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    \"NIA\": {\n" +
            "                        \"label\": \"Niacin (B3)\",\n" +
            "                        \"quantity\": 21.50505959381476,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    \"VITB6A\": {\n" +
            "                        \"label\": \"Vitamin B6\",\n" +
            "                        \"quantity\": 2.2333133464115864,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    \"FOLDFE\": {\n" +
            "                        \"label\": \"Folate equivalent (total)\",\n" +
            "                        \"quantity\": 219.826998122811,\n" +
            "                        \"unit\": \"µg\"\n" +
            "                    },\n" +
            "                    \"FOLFD\": {\n" +
            "                        \"label\": \"Folate (food)\",\n" +
            "                        \"quantity\": 219.826998122811,\n" +
            "                        \"unit\": \"µg\"\n" +
            "                    },\n" +
            "                    \"FOLAC\": {\n" +
            "                        \"label\": \"Folic acid\",\n" +
            "                        \"quantity\": 0,\n" +
            "                        \"unit\": \"µg\"\n" +
            "                    },\n" +
            "                    \"VITB12\": {\n" +
            "                        \"label\": \"Vitamin B12\",\n" +
            "                        \"quantity\": 0.4167379899375,\n" +
            "                        \"unit\": \"µg\"\n" +
            "                    },\n" +
            "                    \"VITD\": {\n" +
            "                        \"label\": \"Vitamin D\",\n" +
            "                        \"quantity\": 0,\n" +
            "                        \"unit\": \"µg\"\n" +
            "                    },\n" +
            "                    \"TOCPHA\": {\n" +
            "                        \"label\": \"Vitamin E\",\n" +
            "                        \"quantity\": 7.252767804050302,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    \"VITK1\": {\n" +
            "                        \"label\": \"Vitamin K\",\n" +
            "                        \"quantity\": 75.81912883253914,\n" +
            "                        \"unit\": \"µg\"\n" +
            "                    },\n" +
            "                    \"WATER\": {\n" +
            "                        \"label\": \"Water\",\n" +
            "                        \"quantity\": 749.290982280275,\n" +
            "                        \"unit\": \"g\"\n" +
            "                    }\n" +
            "                },\n" +
            "                \"totalDaily\": {\n" +
            "                    \"ENERC_KCAL\": {\n" +
            "                        \"label\": \"Energy\",\n" +
            "                        \"quantity\": 38.327326798195664,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"FAT\": {\n" +
            "                        \"label\": \"Fat\",\n" +
            "                        \"quantity\": 52.01409085802059,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"FASAT\": {\n" +
            "                        \"label\": \"Saturated\",\n" +
            "                        \"quantity\": 25.71403698738691,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"CHOCDF\": {\n" +
            "                        \"label\": \"Carbs\",\n" +
            "                        \"quantity\": 23.1856061867442,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"FIBTG\": {\n" +
            "                        \"label\": \"Fiber\",\n" +
            "                        \"quantity\": 64.27714782321476,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"PROCNT\": {\n" +
            "                        \"label\": \"Protein\",\n" +
            "                        \"quantity\": 105.80292439853093,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"CHOLE\": {\n" +
            "                        \"label\": \"Cholesterol\",\n" +
            "                        \"quantity\": 48.28868772291667,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"NA\": {\n" +
            "                        \"label\": \"Sodium\",\n" +
            "                        \"quantity\": 88.2851936263231,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"CA\": {\n" +
            "                        \"label\": \"Calcium\",\n" +
            "                        \"quantity\": 32.1580218717952,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"MG\": {\n" +
            "                        \"label\": \"Magnesium\",\n" +
            "                        \"quantity\": 37.633340322658384,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"K\": {\n" +
            "                        \"label\": \"Potassium\",\n" +
            "                        \"quantity\": 44.975621445067894,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"FE\": {\n" +
            "                        \"label\": \"Iron\",\n" +
            "                        \"quantity\": 23.97349535041917,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"ZN\": {\n" +
            "                        \"label\": \"Zinc\",\n" +
            "                        \"quantity\": 22.764658537285552,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"P\": {\n" +
            "                        \"label\": \"Phosphorus\",\n" +
            "                        \"quantity\": 83.55462179833881,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"VITA_RAE\": {\n" +
            "                        \"label\": \"Vitamin A\",\n" +
            "                        \"quantity\": 25.581584266882565,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"VITC\": {\n" +
            "                        \"label\": \"Vitamin C\",\n" +
            "                        \"quantity\": 278.3430800066108,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"THIA\": {\n" +
            "                        \"label\": \"Thiamin (B1)\",\n" +
            "                        \"quantity\": 55.44319552573785,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"RIBF\": {\n" +
            "                        \"label\": \"Riboflavin (B2)\",\n" +
            "                        \"quantity\": 51.07561836041793,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"NIA\": {\n" +
            "                        \"label\": \"Niacin (B3)\",\n" +
            "                        \"quantity\": 134.40662246134224,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"VITB6A\": {\n" +
            "                        \"label\": \"Vitamin B6\",\n" +
            "                        \"quantity\": 171.79333433935278,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"FOLDFE\": {\n" +
            "                        \"label\": \"Folate equivalent (total)\",\n" +
            "                        \"quantity\": 54.95674953070275,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"VITB12\": {\n" +
            "                        \"label\": \"Vitamin B12\",\n" +
            "                        \"quantity\": 17.3640829140625,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"VITD\": {\n" +
            "                        \"label\": \"Vitamin D\",\n" +
            "                        \"quantity\": 0,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"TOCPHA\": {\n" +
            "                        \"label\": \"Vitamin E\",\n" +
            "                        \"quantity\": 48.35178536033535,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    },\n" +
            "                    \"VITK1\": {\n" +
            "                        \"label\": \"Vitamin K\",\n" +
            "                        \"quantity\": 63.18260736044928,\n" +
            "                        \"unit\": \"%\"\n" +
            "                    }\n" +
            "                },\n" +
            "                \"digest\": [\n" +
            "                    {\n" +
            "                        \"label\": \"Fat\",\n" +
            "                        \"tag\": \"FAT\",\n" +
            "                        \"schemaOrgTag\": \"fatContent\",\n" +
            "                        \"total\": 33.80915905771338,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 52.01409085802059,\n" +
            "                        \"unit\": \"g\",\n" +
            "                        \"sub\": [\n" +
            "                            {\n" +
            "                                \"label\": \"Saturated\",\n" +
            "                                \"tag\": \"FASAT\",\n" +
            "                                \"schemaOrgTag\": \"saturatedFatContent\",\n" +
            "                                \"total\": 5.142807397477381,\n" +
            "                                \"hasRDI\": true,\n" +
            "                                \"daily\": 25.71403698738691,\n" +
            "                                \"unit\": \"g\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"label\": \"Trans\",\n" +
            "                                \"tag\": \"FATRN\",\n" +
            "                                \"schemaOrgTag\": \"transFatContent\",\n" +
            "                                \"total\": 0.013891266331250002,\n" +
            "                                \"hasRDI\": false,\n" +
            "                                \"daily\": 0,\n" +
            "                                \"unit\": \"g\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"label\": \"Monounsaturated\",\n" +
            "                                \"tag\": \"FAMS\",\n" +
            "                                \"schemaOrgTag\": null,\n" +
            "                                \"total\": 21.3065567370985,\n" +
            "                                \"hasRDI\": false,\n" +
            "                                \"daily\": 0,\n" +
            "                                \"unit\": \"g\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"label\": \"Polyunsaturated\",\n" +
            "                                \"tag\": \"FAPU\",\n" +
            "                                \"schemaOrgTag\": null,\n" +
            "                                \"total\": 4.272284663208016,\n" +
            "                                \"hasRDI\": false,\n" +
            "                                \"daily\": 0,\n" +
            "                                \"unit\": \"g\"\n" +
            "                            }\n" +
            "                        ]\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Carbs\",\n" +
            "                        \"tag\": \"CHOCDF\",\n" +
            "                        \"schemaOrgTag\": \"carbohydrateContent\",\n" +
            "                        \"total\": 69.5568185602326,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 23.1856061867442,\n" +
            "                        \"unit\": \"g\",\n" +
            "                        \"sub\": [\n" +
            "                            {\n" +
            "                                \"label\": \"Carbs (net)\",\n" +
            "                                \"tag\": \"CHOCDF.net\",\n" +
            "                                \"schemaOrgTag\": null,\n" +
            "                                \"total\": 53.48753160442891,\n" +
            "                                \"hasRDI\": false,\n" +
            "                                \"daily\": 0,\n" +
            "                                \"unit\": \"g\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"label\": \"Fiber\",\n" +
            "                                \"tag\": \"FIBTG\",\n" +
            "                                \"schemaOrgTag\": \"fiberContent\",\n" +
            "                                \"total\": 16.06928695580369,\n" +
            "                                \"hasRDI\": true,\n" +
            "                                \"daily\": 64.27714782321476,\n" +
            "                                \"unit\": \"g\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"label\": \"Sugars\",\n" +
            "                                \"tag\": \"SUGAR\",\n" +
            "                                \"schemaOrgTag\": \"sugarContent\",\n" +
            "                                \"total\": 46.14711061871982,\n" +
            "                                \"hasRDI\": false,\n" +
            "                                \"daily\": 0,\n" +
            "                                \"unit\": \"g\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"label\": \"Sugars, added\",\n" +
            "                                \"tag\": \"SUGAR.added\",\n" +
            "                                \"schemaOrgTag\": null,\n" +
            "                                \"total\": 0,\n" +
            "                                \"hasRDI\": false,\n" +
            "                                \"daily\": 0,\n" +
            "                                \"unit\": \"g\"\n" +
            "                            }\n" +
            "                        ]\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Protein\",\n" +
            "                        \"tag\": \"PROCNT\",\n" +
            "                        \"schemaOrgTag\": \"proteinContent\",\n" +
            "                        \"total\": 52.901462199265474,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 105.80292439853093,\n" +
            "                        \"unit\": \"g\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Cholesterol\",\n" +
            "                        \"tag\": \"CHOLE\",\n" +
            "                        \"schemaOrgTag\": \"cholesterolContent\",\n" +
            "                        \"total\": 144.86606316875,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 48.28868772291667,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Sodium\",\n" +
            "                        \"tag\": \"NA\",\n" +
            "                        \"schemaOrgTag\": \"sodiumContent\",\n" +
            "                        \"total\": 2118.8446470317544,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 88.2851936263231,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Calcium\",\n" +
            "                        \"tag\": \"CA\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 321.580218717952,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 32.1580218717952,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Magnesium\",\n" +
            "                        \"tag\": \"MG\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 158.0600293551652,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 37.633340322658384,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Potassium\",\n" +
            "                        \"tag\": \"K\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 2113.854207918191,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 44.975621445067894,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Iron\",\n" +
            "                        \"tag\": \"FE\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 4.3152291630754505,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 23.97349535041917,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Zinc\",\n" +
            "                        \"tag\": \"ZN\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 2.5041124391014105,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 22.764658537285552,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Phosphorus\",\n" +
            "                        \"tag\": \"P\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 584.8823525883718,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 83.55462179833881,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Vitamin A\",\n" +
            "                        \"tag\": \"VITA_RAE\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 230.2342584019431,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 25.581584266882565,\n" +
            "                        \"unit\": \"µg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Vitamin C\",\n" +
            "                        \"tag\": \"VITC\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 250.5087720059497,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 278.3430800066108,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Thiamin (B1)\",\n" +
            "                        \"tag\": \"THIA\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 0.6653183463088541,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 55.44319552573785,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Riboflavin (B2)\",\n" +
            "                        \"tag\": \"RIBF\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 0.6639830386854331,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 51.07561836041793,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Niacin (B3)\",\n" +
            "                        \"tag\": \"NIA\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 21.50505959381476,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 134.40662246134224,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Vitamin B6\",\n" +
            "                        \"tag\": \"VITB6A\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 2.2333133464115864,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 171.79333433935278,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Folate equivalent (total)\",\n" +
            "                        \"tag\": \"FOLDFE\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 219.826998122811,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 54.95674953070275,\n" +
            "                        \"unit\": \"µg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Folate (food)\",\n" +
            "                        \"tag\": \"FOLFD\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 219.826998122811,\n" +
            "                        \"hasRDI\": false,\n" +
            "                        \"daily\": 0,\n" +
            "                        \"unit\": \"µg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Folic acid\",\n" +
            "                        \"tag\": \"FOLAC\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 0,\n" +
            "                        \"hasRDI\": false,\n" +
            "                        \"daily\": 0,\n" +
            "                        \"unit\": \"µg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Vitamin B12\",\n" +
            "                        \"tag\": \"VITB12\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 0.4167379899375,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 17.3640829140625,\n" +
            "                        \"unit\": \"µg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Vitamin D\",\n" +
            "                        \"tag\": \"VITD\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 0,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 0,\n" +
            "                        \"unit\": \"µg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Vitamin E\",\n" +
            "                        \"tag\": \"TOCPHA\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 7.252767804050302,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 48.35178536033535,\n" +
            "                        \"unit\": \"mg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Vitamin K\",\n" +
            "                        \"tag\": \"VITK1\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 75.81912883253914,\n" +
            "                        \"hasRDI\": true,\n" +
            "                        \"daily\": 63.18260736044928,\n" +
            "                        \"unit\": \"µg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Sugar alcohols\",\n" +
            "                        \"tag\": \"Sugar.alcohol\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 0,\n" +
            "                        \"hasRDI\": false,\n" +
            "                        \"daily\": 0,\n" +
            "                        \"unit\": \"g\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"label\": \"Water\",\n" +
            "                        \"tag\": \"WATER\",\n" +
            "                        \"schemaOrgTag\": null,\n" +
            "                        \"total\": 749.290982280275,\n" +
            "                        \"hasRDI\": false,\n" +
            "                        \"daily\": 0,\n" +
            "                        \"unit\": \"g\"\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"tags\": [\n" +
            "                    \"Appetizer\",\n" +
            "                    \"salad\",\n" +
            "                    \"Chicken\",\n" +
            "                    \"non-alcoholic\",\n" +
            "                    \"Gluten-free\",\n" +
            "                    \"Poultry\",\n" +
            "                    \"Main Course\",\n" +
            "                    \"grilled\",\n" +
            "                    \"Diet\",\n" +
            "                    \"German\",\n" +
            "                    \"Menopause\",\n" +
            "                    \"Stress\",\n" +
            "                    \"Paleo Diet\",\n" +
            "                    \"Summer\",\n" +
            "                    \"lactose-free\",\n" +
            "                    \"250-400 Calorie\",\n" +
            "                    \"Fertility\",\n" +
            "                    \"High-protien\",\n" +
            "                    \"Mineral-rich\",\n" +
            "                    \"Vitamin-rich\",\n" +
            "                    \"Low-sugar\",\n" +
            "                    \"Lactation\",\n" +
            "                    \"Pregnancy\",\n" +
            "                    \"Paleo Appetizer\",\n" +
            "                    \"Lactose-free Snacks\",\n" +
            "                    \"Meal for Two\",\n" +
            "                    \"Lactose-free Meat\",\n" +
            "                    \"Lactose-free Poultry\",\n" +
            "                    \"Healthy Eyes\",\n" +
            "                    \"Summer Dish\",\n" +
            "                    \"Paleo Snack\",\n" +
            "                    \"Gluten-free Lunch\",\n" +
            "                    \"Gluten-free Poultry Recipe\",\n" +
            "                    \"Low Carb Diet\",\n" +
            "                    \"Gluten-free Dinner\",\n" +
            "                    \"High-protein Poultry Dish\",\n" +
            "                    \"Pegan\"\n" +
            "                ]\n" +
            "            },\n" +
            "            \"_links\": {\n" +
            "                \"self\": {\n" +
            "                    \"title\": \"Self\",\n" +
            "                    \"href\": \"https://api.edamam.com/api/recipes/v2/e82cabb48ac803c29727dfa018a1a334?type=public&app_id=09237e92&app_key=4eb81613c21b788d0b6bc30303d3add0\"\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    ]\n" +
            "}";

}
