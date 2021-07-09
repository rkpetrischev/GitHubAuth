package b.example.githubauth;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyAPICall {
    @GET("{login}")
    Single<User> getUser(
            @Path("login")  String login
    );
    @GET("{login}/followers")
   Single<List<User>> getFollowers (
            @Path("login") String login
    );
}
