package np.com.sudishrestha.www.galleryview;
public class Gallery {
    public int Image;
    public String Name;

    public Gallery(String Name, int Image) {
        this.Image = Image;
        this.Name = Name;
    }
    public int getImage() {
        return Image;
    }

    public void setImage(int productImage) {
        this.Image = productImage;
    }

    public String getName() {
        return Name;
    }

    public void setName(String productName) {
        this.Name = productName;
    }
}