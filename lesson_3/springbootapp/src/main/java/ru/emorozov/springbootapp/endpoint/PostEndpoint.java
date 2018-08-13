package ru.emorozov.springbootapp.endpoint;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.emorozov.springbootapp.entity.Post;
import ru.emorozov.springbootapp.service.PostService;
import ru.emorozov.springbootapp.soap.post.*;

import java.util.List;
import java.util.logging.Logger;

@Endpoint
public class PostEndpoint {

    @NotNull
    private final static Logger LOGGER = Logger.getLogger(PostEndpoint.class.getSimpleName());

    @NotNull
    public final static String LOCATION_URI = "/soap/post";

    @NotNull
    public final static String PORT_TYPE_NAME = "PostEndpointPort";

    @NotNull
    public final static String NAMESPACE = "http://emorozov.ru/springbootapp/soap/post";

    @Autowired
    private PostService postService;

    @ResponsePayload
    @PayloadRoot(localPart = "pingRequest", namespace = NAMESPACE)
    public PingResponse ping(@RequestPayload final PingRequest request) {
        final PingResponse result = new PingResponse();
        result.setSuccess(true);
        return result;
    }

    @ResponsePayload
    @PayloadRoot(localPart = "getPostsRequest", namespace = NAMESPACE)
    public GetPostsResponse getPosts(@RequestPayload final GetPostsRequest request) {
        final GetPostsResponse result = new GetPostsResponse();
        try {
            final List<Post> posts = postService.getListPost();
            for (Post post: posts) {
                result.getRows().add(toRecord(post));
            }
        } catch (@NotNull final Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return result;
    }

    @ResponsePayload
    @PayloadRoot(localPart = "removePostRequest", namespace = NAMESPACE)
    public RemovePostResponse removePost(@RequestPayload final RemovePostRequest request) {
        final RemovePostResponse result = new RemovePostResponse();
        try {
            postService.removePostById(request.getId());
            result.setSuccess(true);
        } catch (@NotNull final Exception e) {
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }


    @ResponsePayload
    @PayloadRoot(localPart = "createPostRequest", namespace = NAMESPACE)
    public CreatePostResponse createPost(@RequestPayload final CreatePostRequest request) {
        final CreatePostResponse result = new CreatePostResponse();
        try {
            postService.createPost(
                    request.getUser(),
                    request.getTitle(),
                    request.getDescription(),
                    request.getContent());
            result.setSuccess(true);
        } catch (@NotNull final Exception e) {
            LOGGER.severe(e.getMessage());
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @NotNull
    private PostRecord toRecord(@NotNull final Post post) {
        final PostRecord record = new PostRecord();
        record.setId(post.getId());
        record.setUser(post.getUser().getId());
        record.setTitle(post.getTitle());
        record.setDescription(post.getDescription());
        record.setContent(post.getContent());
        return record;
    }

}
