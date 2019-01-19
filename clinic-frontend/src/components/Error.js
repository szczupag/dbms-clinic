import React from 'react';

const Error = props => {
    return(
        <div className="errorPopup" onClick={props.onClick}>
            <div className="messageWrapper">
                <p>ERROR FROM SERVER</p>
                <p>{props.message}</p>
            </div>
        </div>
    );
}

export default Error;
// 