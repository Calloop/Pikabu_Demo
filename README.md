# Pikabu_Demo

Приложение разрабатывается в целях обучения, в виде ленты постов, подобно существующему приложению Pikabu.
Использованные технологии: Java, MVVM, Room, SQLite.
Также использовал RecyclerView (с RecycledViewPool и multiple ViewHolders), NavigationUI,
ViewModel (с savedStateInstance), ViewBinding, SharedPreferences, Async Threads, Activity Result API.

В процессе изучения и добавления: медиа-контент, DI (dagger2), unit-tests, remote DB (retrofit), RxJava.

Имеющийся функционал:
1. Регистрация и авторизация пользователя;
2. Создание собственного поста (только текст);
3. Просмотр ленты постов (берутся из локальной БД, изначально лента пустая и нуждается в заполнении).
4. Данные в процессе использования не теряются, благодаря использованию ViewModel с SavedStateInstance и SharedPreferences.
