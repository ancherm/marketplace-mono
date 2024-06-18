import React, { useEffect, useState } from 'react';
import axios from 'axios';
import SellerService from "../../API/SellerService";

const AddProductPage = () => {
    const [product, setProduct] = useState({
        name: '',
        category: '',
        price: '',
        quantity: '',
        description: '',
        photos: [],
        characteristics: [{ key: '', value: '' }]
    });

    const [categories, setCategories] = useState([]);

    useEffect(() => {
        const fetchCategories = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/categories/get-all');
                setCategories(response.data);
            } catch (error) {
                console.error('Error fetching categories', error);
            }
        };

        fetchCategories();
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setProduct((prevProduct) => ({
            ...prevProduct,
            [name]: value,
        }));
    };

    const handleFileChange = (e) => {
        const files = Array.from(e.target.files);
        setProduct((prevProduct) => ({
            ...prevProduct,
            photos: files,
        }));
    };

    const handleAttributeChange = (index, e) => {
        const { name, value } = e.target;
        const characteristics = [...product.characteristics];
        characteristics[index][name] = value;
        setProduct({ ...product, characteristics });
    };

    const addCharacteristic = () => {
        setProduct({ ...product, characteristics: [...product.characteristics, { key: '', value: '' }] });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append('name', product.name);
        formData.append('category', product.category);
        formData.append('price', product.price);
        formData.append('quantity', product.quantity);
        formData.append('description', product.description);

        // Append photos with the correct name 'photos'
        product.photos.forEach((photo, index) => {
            formData.append('photos', photo); // 'photos' should match the server's expected parameter name
        });

        // Append characteristics as well, using nested structure
        product.characteristics.forEach((characteristic, index) => {
            formData.append(`characteristics[${index}][key]`, characteristic.key);
            formData.append(`characteristics[${index}][value]`, characteristic.value);
        });

        try {
            await SellerService.addProduct(formData); // Ensure to await the promise here
            alert('Product added successfully');
        } catch (error) {
            console.error('Error adding product', error);
            alert('Failed to add product');
        }
    };


    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Название</label>
                <input type="text" name="name" value={product.name} onChange={handleChange} required />
            </div>
            <div>
                <label>Категория</label>
                <select name="category" value={product.category} onChange={handleChange} required>
                    <option value="">Выберите категорию</option>
                    {categories.map((category) => (
                        <option key={category.id} value={category.name}>
                            {category.name}
                        </option>
                    ))}
                </select>
            </div>
            <div>
                <label>Цена</label>
                <input type="number" name="price" value={product.price} onChange={handleChange} required />
            </div>
            <div>
                <label>Количество</label>
                <input type="number" name="quantity" value={product.quantity} onChange={handleChange} required />
            </div>
            <div>
                <label>Описание</label>
                <textarea name="description" value={product.description} onChange={handleChange} />
            </div>
            <div>
                <label>Фотографии</label>
                <input type="file" accept="image/*" name="photos" multiple onChange={handleFileChange} />

                <h3>Характеристики</h3>
                {product.characteristics.map((characteristic, index) => (
                    <div key={index}>
                        <input type="text" name="key" placeholder="Название характеристики" value={characteristic.key} onChange={(e) => handleAttributeChange(index, e)} />
                        <input type="text" name="value" placeholder="Значение характеристики" value={characteristic.value} onChange={(e) => handleAttributeChange(index, e)} />
                    </div>
                ))}
                <button type="button" onClick={addCharacteristic}>Добавить характеристику</button>
            </div>
            <button type="submit">Добавить товар</button>
        </form>
    );
};

export default AddProductPage;
