import React from 'react';
import constants from '../../constants/pages';

const Clinic = props => {
    const departments = props.data.departments.map((department,key)=>{
        return <p key={key}>{department.name}</p>
    })
    return(
        <div className="item-wrapper">
            <div className="item-header">
                <p>{props.data.name}</p>
            </div>
            <div className="item-content">
                <p className="label">type:</p>
                <p>{props.data.type}</p>
                <p className="label">departments:</p>
                    {departments}
                <p className="label">localization:</p>                
                <p>{props.data.localization.city+" "+props.data.localization.postalCode+" "+props.data.localization.street+" "+props.data.localization.buildingNo}</p>
                
            </div>
            <div className="item-footer">
                <div className="controls">
                    <button 
                        className="controls-btn edit" 
                        onClick={()=>{
                            props.editItemHandler(props.data);
                            props.changePanel(constants.EDIT_CLINIC);
                        }}>Edit</button>
                    <button 
                        className="controls-btn delete" 
                        onClick={()=>props.deleteHandler(constants.CLINICS,props.data.id)}>Delete</button>
                </div>
            </div>
        </div>
    );
}

export default Clinic;