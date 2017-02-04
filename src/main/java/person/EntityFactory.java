package person;

public class EntityFactory {

	public PersonEntity PersonEntity(String criteria) {
		if (criteria.equals("customer"))
			return new Customer();
		else if (criteria.equals("employee"))
			return new Employee();
		return null;
	}
}
