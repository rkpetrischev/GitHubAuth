package b.example.githubauth.screens.fragments.flfragmentmvp;


interface FollowerView {

    void setId(int id);

    void setLogin(String login);

    void setNodeId(String nodeId);

    void setImage(String imageUrl);

    void onFollowerClick();
}