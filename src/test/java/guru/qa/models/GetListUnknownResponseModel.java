package guru.qa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetListUnknownResponseModel {

    int page, total;

    @JsonProperty("per_page")
    int perPage;
    @JsonProperty("total_pages")
    int totalPages;

    List<UserListData> data;

    Support support;

    @Data
    public static class UserListData {
        int id, year;
        String name, color, pantone_value;
    }

    @Data
    public static class Support {
        String url, text;
    }
}
