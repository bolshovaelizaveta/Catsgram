package ru.yandex.practicum.catsgram.dal.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.catsgram.model.Image;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ImageRowMapper implements RowMapper<Image> {
    @Override
    public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
        Image image = new Image();
        image.setId(rs.getLong("id"));
        image.setPostId(rs.getLong("post_id"));
        image.setOriginalFileName(rs.getString("original_name"));
        image.setFilePath(rs.getString("file_path"));
        return image;
    }
}