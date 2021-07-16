package b.example.githubauth.screens.fragments.flfragmentmvp;


import java.util.List;

import b.example.githubauth.model.Model;
import b.example.githubauth.model.User;

public class UserPresenter {

    private List<User> users;

    public UserPresenter(List<User> users) {
        this.users = users;
    }

    public void setUsers(String login) {
        Model model = new Model();
        model.setUsers(login);
        this.users=model.getUsers();
    }


    public void onBindRepositoryRowViewAtPosition(int position, FollowerView followerView) {
        User user = users.get(position);
        followerView.setId(user.getId());
        followerView.setLogin(user.getLogin());
        followerView.setImage(user.getAvatarUrl());
        followerView.setNodeId(user.getNodeId());
        followerView.onFollowerClick();
    }

    public int getRepositoriesRowsCount() {
        return users.size();
    }


}