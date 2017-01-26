package Business.Person;


public class EntityFactory {

	public PersonEntity PersonEntity(String criteria) {
		if (criteria.equals("customer"))
			return new Customer();
		else if (criteria.equals("washStationEmployee"))
			return new Customer();
		return null;
	}
}
