package group25.group25.board.model;

import javax.persistence.*;
import group25.group25.workspace.model.Workspace;

@Entity
@Table(name = "boards")
public class Board {

    public Board() {
    }

    public Board(Long boardId, String dateCreated,
                 String description, String boardTitle) {
        this.id = boardId;

        this.dateCreated = dateCreated;
        this.description = description;
        this.title = boardTitle;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "board_title")
    private String title;

    @Column(name = "board_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    @Column(name = "date_created")
    private String dateCreated;

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
