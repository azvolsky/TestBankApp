INSERT INTO CLIENTS(Id, Name, Address, Age) VALUES(1, 'Andrew', 'some street1', 22);
INSERT INTO CLIENTS(Id, Name, Address, Age) VALUES(2, 'John', 'some street2', 32);

INSERT INTO ACCOUNTS(Id, Client_Id, Amount) VALUES(1, 1, 3000);
INSERT INTO ACCOUNTS(Id, Client_Id, Amount) VALUES(2, 2, 5000);

INSERT INTO TRANSACTIONS(Id, Account_Id_Dst, Account_Id_Src, Transfer, Finished, StartTX, EndTX) VALUES(1, 1, 2, 1000, 0, CURDATE(), CURDATE());

