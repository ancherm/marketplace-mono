import * as React from 'react';
import classes from "./InputField.module.css";

const InputField = React.forwardRef((props, ref) => {
    return (
        <input ref={ref} className={classes.inputField} {...props}/>
    );
});

export default InputField;