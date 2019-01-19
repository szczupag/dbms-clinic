import React from 'react';

const Popup = props => {
    return(
        <div className="errorPopup" onClick={props.onClick}>
            <div className="messageWrapper">
                <p>{props.message}</p>
            </div>
        </div>
    );
}

export default Popup;
// 