## DDL for Data Model

```sqlite
CREATE TABLE IF NOT EXISTS `Income`
(
    `id`     INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `amount` REAL                              NOT NULL,
    `date`   TEXT
);

CREATE TABLE IF NOT EXISTS `Category`
(
    `id`      INTEGER NOT NULL,
    `percent` REAL    NOT NULL,
    `info`    TEXT,
    `payout`  REAL    NOT NULL,
    `name`    TEXT,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `Expense`
(
    `id`          INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `amount`      INTEGER                           NOT NULL,
    `title`       TEXT,
    `category_id` INTEGER                           NOT NULL,
    FOREIGN KEY (`category_id`) REFERENCES `Category` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE INDEX `index_Expense_category_id` ON `Category` (`id`);
```
+ [Readme](https://treypage.github.io/budget-backwards/)
+ [User stories](user-stories.md)
+ [ERD](ERD.md)
+ [Milestone 2](milestone-2.md)
