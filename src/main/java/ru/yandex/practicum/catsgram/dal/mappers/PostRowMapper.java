package ru.yandex.practicum.catsgram.dal.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PostRowMapper implements RowMapper<Post> {

    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();
        post.setId(rs.getLong("id"));
        post.setDescription(rs.getString("description"));
        post.setPostDate(rs.getTimestamp("post_date").toInstant());

        User author = new User();
        author.setId(rs.getLong("author_id"));
        post.setAuthor(author);

        return post;
    }
}
