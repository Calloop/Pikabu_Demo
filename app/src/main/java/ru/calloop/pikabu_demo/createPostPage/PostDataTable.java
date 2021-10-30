package ru.calloop.pikabu_demo.createPostPage;

public class PostDataTable {
    public static final String TABLE = "postData";

    public static class COLUMN {
        public static final String ID = "_id";
        public static final String POST_ID = "postId";
        public static final String DATA_POSITION = "dataPosition";
        public static final String DATA_TYPE = "dataType";
        public static final String DATA_VALUE = "dataValue";
    }

    public static final String CREATE_SCRIPT =
            String.format("create table %s ("
                            + "%s integer primary key autoincrement,"
                            + "%s integer,"
                            + "%s integer,"
                            + "%s integer,"
                            + "%s text" + ");",
                    TABLE, COLUMN.ID, COLUMN.POST_ID, COLUMN.DATA_POSITION, COLUMN.DATA_TYPE, COLUMN.DATA_VALUE);
}
