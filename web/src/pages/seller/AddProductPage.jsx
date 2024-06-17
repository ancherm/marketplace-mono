import React, { useState } from 'react';
import InputField from "../../components/ui/input/InputField";
import Button from "@mui/material/Button";
import classes from "./AddProductPage.module.css";
import Select from "../../components/ui/select/Select";

const AddProductPage = () => {
    const [characteristicsCount, setCharacteristicsCount] = useState(1);

    const handleCharacteristicsCountChange = (value) => {
        setCharacteristicsCount(Number(value));
    };

    const renderCharacteristicsFields = () => {
        let fields = [];
        for (let i = 0; i < characteristicsCount; i++) {
            fields.push(
                <InputField
                    key={i}
                    type="text"
                    placeholder={`Характеристика ${i + 1}`}
                />
            );
        }
        return fields;
    };

    return (
        <div className={classes.form}>
            <h1>Добавление товара</h1>
            <form style={{ width: "75%", marginTop: "30px", display: "flex", flexDirection: "column" }}>
                <InputField type="text" placeholder="Название" />
                <Select
                    defaultValue="Категория"
                    option={[
                        { value: "Быт", name: "Быт" },
                        { value: "Техника", name: "Техника" },
                    ]}
                />
                <InputField type="number" min="0" placeholder="Цена" />
                <InputField type="number" min="1" placeholder="Количество" />
                <InputField type="text" placeholder="Описание" />
                <Select
                    defaultValue="Количество характеристик"
                    option={[
                        { value: 1, name: "1" },
                        { value: 2, name: "2" },
                        { value: 3, name: "3" },
                        { value: 4, name: "4" },
                        { value: 5, name: "5" },
                    ]}
                    onChange={handleCharacteristicsCountChange}
                />
                <div style={{paddingLeft: "20px", paddingRight: "20px", display: "flex", flexDirection: "column"}}>
                    {renderCharacteristicsFields()}
                </div>
                <InputField type="file" accept="image/*" placeholder="Фото товара" />
                <Button
                    variant="contained"
                    style={{
                        borderRadius: "10px",
                        fontSize: "14px",
                        cursor: "pointer",
                        backgroundColor: "limegreen",
                        color: "black",
                        marginTop: "20px"
                    }}
                >
                    Добавить товар
                </Button>
            </form>
        </div>
    );
};

export default AddProductPage;
