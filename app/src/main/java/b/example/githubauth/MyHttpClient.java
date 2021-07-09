package b.example.githubauth;


import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class MyHttpClient extends OkHttpClient {

    public static OkHttpClient create() {
        return new OkHttpClient.Builder()
                .addInterceptor(new GitHubAuthInterceptor())
                .build();
    }

    private static class GitHubAuthInterceptor implements Interceptor {
        @Override
        public @NotNull Response intercept(@NonNull Chain chain) throws IOException {



            return chain.proceed(chain.request());
        }
    }
}

