import java.io.Serializable;

/**
 * Class that represents a group of unchangeable variables.
 * Necessary to specify the type of clients' requests.
 */
public enum RequestType implements Serializable {
    CHATROOM_CHANGE,
    CREATE_CHATROOM,
    GET_CHATROOM_LIST;
}
