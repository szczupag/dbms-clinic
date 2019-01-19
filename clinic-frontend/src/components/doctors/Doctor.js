import React from 'react';
import constants from '../../constants/pages';

const Doctor = props => {
    return(
        <div className="item-wrapper">
            <div className="item-header">
                <p>{props.data.firstName+" "+props.data.lastName}</p>
            </div>
            <div className="item-content">
                <p className="label">pesel:</p>
                <p>{props.data.pesel}</p>
                <p className="label">salary:</p>
                <p>{props.data.salary}</p>
                <p className="label">speciality:</p>
                <p>{props.data.speciality}</p>
                <p className="label">supervisor:</p>
                <p>{props.data.supervisor!=undefined?props.data.supervisor.firstName+" "+props.data.supervisor.lastName:<span className="empty">No supervisor assigned</span>}</p>
                <p className="label">department:</p>
                <p>{props.data.department!=undefined?<span>{props.data.department.name}<span className="empty">{props.data.department.clinic.name}</span></span>:<span className="empty">No department assigned</span>}</p>
                {/* <p></p> */}
            </div>
            <div className="item-footer">
                <div className="controls">
                    <button 
                        className="controls-btn edit" 
                        onClick={()=>{
                            props.editItemHandler(props.data);
                            props.changePanel(constants.EDIT_DOCTOR);
                        }}>Edit</button>
                    <button 
                        className="controls-btn delete" 
                        onClick={()=>props.deleteHandler(constants.DOCTORS,props.data.pesel)}>Delete</button>
                </div>
            </div>
        </div>
    );
}

export default Doctor;