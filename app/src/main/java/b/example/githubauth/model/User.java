package b.example.githubauth.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
        @SerializedName("login")
        @Expose
        private final String login;
        @SerializedName("id")
        @Expose
        private final int id;
        @SerializedName("node_id")
        @Expose
        private final String nodeId;

        @SerializedName("avatar_url")
        @Expose
        private final String avatarUrl;

        public User(String username, int id, String nodeId, String avatarUrl){
            this.login= username;
            this.id=id;
            this.nodeId=nodeId;
            this.avatarUrl = avatarUrl;
        }

        public String getLogin() {
            return login;
        }


        public int getId() {
            return id;
        }


        public String getNodeId() {
            return nodeId;
        }


        public String getAvatarUrl() {
            return avatarUrl;
        }


}
