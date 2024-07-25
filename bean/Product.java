package bean;

	public class Product {
	    private int id;
	    private String name;
	    private int brand_id;
	    private String brand_name;
	    private int price;
	    private String color;
	    private String specification;
	    private String image;
	    
	    
	    
	    public Product(int id, String name, int brand_id, String brand_name, int price, String color,
				String specification, String image) {
			super();
			this.id = id;
			this.name = name;
			this.brand_id = brand_id;
			this.brand_name = brand_name;
			this.price = price;
			this.color = color;
			this.specification = specification;
			this.image = image;
		}
		public Product() {
			super();
		}
		public Product(int id, String name, int price) {
	        this.id = id;
	        this.name = name;
	        this.price = price;
	    }
		
		public String getBrand_name() {
			return brand_name;
		}
		public void setBrand_name(String brand_name) {
			this.brand_name = brand_name;
		}
		public int getBrand_id() {
			return brand_id;
		}
		public void setBrand_id(int brand_id) {
			this.brand_id = brand_id;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public String getSpecification() {
			return specification;
		}
		public void setSpecification(String specification) {
			this.specification = specification;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		
}
