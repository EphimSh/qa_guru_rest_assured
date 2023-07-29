package guru.qa.models;

import lombok.Data;

import java.util.List;

@Data
public class GetListUnknownResponseModel {

    int page, per_page, total, total_pages;

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
