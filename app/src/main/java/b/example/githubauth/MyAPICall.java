package b.example.githubauth;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface MyAPICall {
    @GET("{login}")
    @Headers({"Authorization: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"})
    Single<User> getUser(
            @Path("login")  String login
    );
    @GET("{login}/followers")
    @Headers({"Authorization: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"})
   Single<List<User>> getFollowers (
            @Path("login") String login
    );
}
