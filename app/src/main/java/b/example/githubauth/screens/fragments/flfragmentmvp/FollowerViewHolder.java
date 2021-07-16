package b.example.githubauth.screens.fragments.flfragmentmvp;


import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import b.example.githubauth.R;
import b.example.githubauth.screens.MainActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class FollowerViewHolder extends RecyclerView.ViewHolder implements FollowerView {

    TextView id;
    TextView login;
    TextView nodeId;
    CircleImageView circleImageView;
    CardView cardView;

    public FollowerViewHolder(View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.id);
        nodeId = itemView.findViewById(R.id.node_id);
        login = itemView.findViewById(R.id.username);
        circleImageView = itemView.findViewById(R.id.circle_avatar);
        cardView = itemView.findViewById(R.id.card_view);
    }

    @Override
    public void setId(int id) {
        this.id.setText(String.valueOf(id));
    }

    @Override
    public void setLogin(String login) {
        this.login.setText(login);
    }

    @Override
    public void setNodeId(String nodeId) {
        this.nodeId.setText(nodeId);
    }

    @Override
    public void setImage(String imageUrl) {
        Picasso.get().load(imageUrl).into(this.circleImageView);
    }

    @Override
    public void onFollowerClick() {
        this.cardView.setOnClickListener(v -> MainActivity.start(cardView.getContext(), this.login.getText().toString()));
    }


}