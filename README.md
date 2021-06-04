Coverage: 70.2%
# Inventory Management System

IMS project that will interact through CRUD(create, read, update, delete) services on a MySQL database.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Install Java 14 to desktop and Maven

### Installing

Copy git respository to local machine

```
git clone https://github.com/RobinKhiv/IMS-Starter
```

then import the file into Eclipse. After you would need to setup your database by creating a db.properties src/main/resouces the db.properties should be in the form of:

```
db.url=jdbc:mysql://localhost:portnumber/ims
db.user=youruser
db.password=yourpassword

```
A additional file dbTest.properties will have to be created in src/test/resources if you would like to run the unit test on the application. The dbTest.properties will consist of:
```
db.url=jdbc:mysql://localhost:portnumber/imsTest
db.user=youruser
db.password=yourpassword
```
You will also have to create additional database through MySql command client or workspace. The database you will need to create are ims and imsTest.

After creating the ims database you will need to run both sql files under src/main/resources to create the entities required for the database.


## Running the tests

The test can be ran in Eclipse by right clicking the folder src/test/java and then going to run as and clicking on junit 5.


### Unit Tests 

There are specific test for Controllers, Database Access Objects and Model Classes.
Controller Test will test each controller class on the main program to make confirm the expected outcome of each method.

```	@Test
	public void testCreate() {
		final String F_NAME = "barry", L_NAME = "scott";
		final Customer created = new Customer(F_NAME, L_NAME);

		Mockito.when(utils.getString()).thenReturn(F_NAME, L_NAME);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}
```
Database Access Object test will test that each query into the database will return an expected result

```
	@Test
	public void testCreate() {
		final Item created = new Item(2l, "test item", 10.00d);
		assertEquals(created, DAO.create(created));
	}
```
Model Class tests will that each function and constructor will return the expected result.

```
@Test
	public void itemConstructorTest() {
		final Item i = new Item(1l);
		assertNotNull(i);
		assertTrue(i instanceof Item);
	}

```


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Eclipse](https://www.eclipse.org/) - IDE Compiler
* [JUnit 5](https://junit.org/junit5/) - Testing
* [MySQL](https://www.mysql.com/) - Database

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

