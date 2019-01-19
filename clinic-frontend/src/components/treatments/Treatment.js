import React from 'react';
import constants from '../../constants/pages';

const Treatment = props => {
    const medicalProcedures = props.data.medicalProcedures.map((med,key)=>{
        return <p key={key}>{med.name}</p>
    })
    console.log(props.data)
    return(
        <div className="item-wrapper">
            <div className="item-content">
                <p className="label">patient:</p>
                <p><span>{props.data.patient.firstName+" "+props.data.patient.lastName}</span><span className="empty">{props.data.patient.pesel}</span></p>
                <p className="label">disease:</p>
                <p>{props.data.disease!=undefined?<span>{props.data.disease.name}<span className="empty">{props.data.disease.severity}</span></span>:<span className="empty">No diseases assigned</span>}</p>
                <p className="label">start date:</p>
                <p>{props.data.startDate}</p>
                <p className="label">end date:</p>
                <p>{props.data.endDate}</p>
                <p className="label">medical procedures:</p>
                    {medicalProcedures}
            </div>
            <div className="item-footer">
                <div className="controls">
                    <button 
                        className="controls-btn edit" 
                        onClick={()=>{
                            props.editItemHandler(props.data);
                            props.changePanel(constants.EDIT_TREATMENT);
                        }}>Edit</button>
                    <button 
                        className="controls-btn delete" 
                        onClick={()=>props.deleteHandler(constants.TREATMENTS,props.data.id)}>Delete</button>
                </div>
            </div>
        </div>
    );
}

export default Treatment;