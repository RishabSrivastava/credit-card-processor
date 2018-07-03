create table creditcardinfo
(
	id FLOAT not null,
	name VARCHAR not null,
	balance FLOAT not null,
	primary key(id)
);

create table cardLedger
(
	id FLOAT not null,
	availablebalance FLOAT,
	outstandingbalance FLOAT,
	FOREIGN KEY (id) REFERENCES public.creditcardinfo(id)
);