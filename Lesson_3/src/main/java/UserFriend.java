// Класс UserFriend представляет собой модель для хранения информации о дружбе между пользователями.
public class UserFriend {
    // Поля для хранения идентификаторов пользователя и его друга.
    int userId, friendId;

    // Конструктор класса UserFriend. Принимает идентификатор друга и пользователя.
    public UserFriend(int friendId, int userId){
        // Инициализация поля friendId значением, переданным в конструктор.
        this.friendId = friendId;
        // Инициализация поля userId значением, переданным в конструктор.
        this.userId = userId;
    }
}
