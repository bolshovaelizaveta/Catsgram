package ru.yandex.practicum.catsgram.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.catsgram.dal.mappers.PostRowMapper;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.SortOrder;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository extends BaseRepository<Post> {

    private static final String FIND_ALL_QUERY = "SELECT * FROM posts ORDER BY post_date %s LIMIT ? OFFSET ?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM posts WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO posts(author_id, description, post_date) " +
            "VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE posts SET description = ? WHERE id = ?";

    public PostRepository(JdbcTemplate jdbc, PostRowMapper mapper) {
        super(jdbc, mapper, Post.class);
    }

    public List<Post> findAll(Integer size, SortOrder sort, Integer from) {
        String sortOrder = (sort == SortOrder.DESCENDING ? "DESC" : "ASC");
        String formattedQuery = String.format(FIND_ALL_QUERY, sortOrder);
        return findMany(formattedQuery, size, from);
    }

    public Optional<Post> findById(long postId) {
        return findOne(FIND_BY_ID_QUERY, postId);
    }

    public Post save(Post post) {
        long id = insert(
                INSERT_QUERY,
                post.getAuthor().getId(),
                post.getDescription(),
                Timestamp.from(post.getPostDate())
        );
        post.setId(id);
        return post;
    }

    public Post update(Post post) {
        update(
                UPDATE_QUERY,
                post.getDescription(),
                post.getId()
        );
        return post;
    }
}