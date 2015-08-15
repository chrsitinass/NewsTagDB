**NewsTagDB**
-------------
Some Mysql operations between two databases.

**database**:*newsProject*
**table**:	*news*

    +------------------+--------------+------+-----+---------+----------------+
    | Field            | Type         | Null | Key | Default | Extra          |
    +------------------+--------------+------+-----+---------+----------------+
    | id               | int(11)      | NO   | PRI | NULL    | auto_increment |
    | title            | varchar(300) | YES  |     | NULL    |                |
    | source           | varchar(100) | YES  |     | NULL    |                |
    | topic_id         | int(11)      | YES  |     | NULL    |                |
    | pubtime          | datetime     | YES  |     | NULL    |                |
    | URL              | varchar(255) | YES  |     | NULL    |                |
    | category         | varchar(255) | YES  |     | NULL    |                |
    | content          | longtext     | YES  |     | NULL    |                |
    | content_with_url | longtext     | YES  |     | NULL    |                |
    | outer_id         | varchar(150) | YES  | UNI | NULL    |                |
    +------------------+--------------+------+-----+---------+----------------+

**database**:*NewsTag*
**table**:	*NewsList*

    +-------------+--------------+------+-----+-------------------+----------------+
    | Field       | Type         | Null | Key | Default           | Extra          |
    +-------------+--------------+------+-----+-------------------+----------------+
    | docId       | bigint(20)   | NO   | PRI | NULL              | auto_increment |
    | cate        | int(11)      | YES  |     | 0                 |                |
    | subCate     | int(11)      | YES  |     | 0                 |                |
    | thiCate     | int(11)      | YES  |     | 0                 |                |
    | title       | text         | NO   |     | NULL              |                |
    | content     | longtext     | NO   |     | NULL              |                |
    | Tagged      | varchar(255) | YES  |     | NULL              |                |
    | source      | varchar(20)  | YES  | MUL | NULL              |                |
    | sourceId    | varchar(80)  | YES  |     | NULL              |                |
    | ccncCate    | int(11)      | YES  |     | 0                 |                |
    | ccncSubCate | int(11)      | YES  |     | 0                 |                |
    | ccncThiCate | int(11)      | YES  |     | 0                 |                |
    | Timestamp   | timestamp    | YES  |     | CURRENT_TIMESTAMP |                |
    | markedlabel | text         | YES  |     | NULL              |                |
    | pubtime     | datetime     | YES  |     | NULL              |                |
    | fakeDocId   | bigint(20)   | YES  | UNI | 0                 |                |
    +-------------+--------------+------+-----+-------------------+----------------+

**database**:*NewsTag*
**table**:	*NewsHash*

    +-------------+--------------+------+-----+---------+-------+
    | Field       | Type         | Null | Key | Default | Extra |
    +-------------+--------------+------+-----+---------+-------+
    | docId       | bigint(20)   | NO   | PRI | NULL    |       |
    | title       | text         | NO   |     | NULL    |       |
    | source      | varchar(20)  | NO   |     | NULL    |       |
    | sourceId    | varchar(100) | NO   |     | NULL    |       |
    | cate        | int(11)      | YES  |     | 0       |       |
    | subCate     | int(11)      | YES  |     | 0       |       |
    | thiCate     | int(11)      | YES  |     | 0       |       |
    | contentHash | varchar(513) | NO   |     | NULL    |       |
    | isDupNews   | int(8)       | YES  |     | 0       |       |
    | note        | varchar(500) | YES  |     |         |       |
    +-------------+--------------+------+-----+---------+-------+`
meaning of `isDupNews`:
 - 0: correct news
 - 1: duplicate news to be saved
 - 2: duplicate new to be removed
 - 3: news to be removed, detected by title length and content length
 - 4: duplicate news to be removed, detected by title
 - 5: category id not match!
 - 10: other news to be removed