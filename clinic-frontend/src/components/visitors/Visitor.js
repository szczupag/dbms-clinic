import React from 'react';
import constants from '../../constants/pages';

const Visitor = props => {
    const visits = props.data.visits.map((visit,index)=>{
        return <div key={index} className="insideContainer">
                    <p><span className="empty">patient: </span>{visit.patient.firstName+" "+visit.patient.lastName}</p>
                    <p><span className="empty">date: </span>{visit.visitDate}</p>
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
                <p className="label">id number:</p>
                <p>{props.data.idNumber}</p>
                <p className="label">visits:</p>
                {visits}
            </div>
            <div className="item-footer">
                <div className="controls">
                    <button 
                        className="controls-btn edit" 
                        onClick={()=>{
                            props.editItemHandler(props.data);
                            props.changePanel(constants.EDIT_VISITOR);
                        }}>Edit</button>
                    <button 
                        className="controls-btn delete" 
                        onClick={()=>props.deleteHandler(constants.VISITORS,props.data.pesel)}>Delete</button>
                </div>
            </div>
        </div>
    );
}

export default Visitor;