import React from 'react';
import constants from '../../constants/pages';

const Patient = props => {
    const treatments = props.data.treatments.map((treatment,key)=>{
        let doctors = [];
        treatment.medicalProcedures.map((med)=>{
            med.doctors.map((doc,key)=>{
                doctors.push(<p key={key}>{doc.firstName+" "+doc.lastName}</p>)
            })
        });
        const medProc = treatment.medicalProcedures.map((med,key)=>{
            return <p key={key}><span className="empty">medical procedure: </span>{med.name}</p>
        });
        return <div key={key} className="insideContainer">
                    {medProc}
                    <div>
                        <span className="empty">doctors:</span>
                        {doctors}
                    </div>
                    <p><span className="empty">start date: </span>{treatment.startDate}</p>
                    <p><span className="empty">end date: </span>{treatment.endDate}</p>
                </div>
    })
    return(
        <div className="item-wrapper">
            <div className="item-header">
                <p>{props.data.firstName+" "+props.data.lastName}</p>
            </div>
            <div className="item-content">
                <p className="label">pesel:</p>
                <p>{props.data.pesel}</p>
                <p className="label">phoneNumber:</p>
                <p>{props.data.phoneNumber}</p>
                <p className="label">treatments:</p>
                    {treatments}
            </div>
            <div className="item-footer">
                <div className="controls">
                    <button 
                        className="controls-btn edit" 
                        onClick={()=>{
                            props.editItemHandler(props.data);
                            props.changePanel(constants.EDIT_PATIENT);
                        }}>Edit</button>
                    <button 
                        className="controls-btn delete" 
                        onClick={()=>props.deleteHandler(constants.PATEINTS,props.data.pesel)}>Delete</button>
                    <button 
                        className="controls-btn count" 
                        onClick={()=>props.countVisitors(props.data.pesel)}>Count visits</button>
                </div>
            </div>
        </div>
    );
}

export default Patient;